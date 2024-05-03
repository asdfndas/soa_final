import firebase_admin
from firebase_admin import firestore
from firebase_admin import credentials
from models import *
from fastapi import HTTPException
from typing import List, Tuple
import random
import string
import cloudinary
import cloudinary.uploader

cred = credentials.Certificate("credentials.json")
firebase_admin.initialize_app(cred)

db = firestore.client()

def create_user(password: str):
    try:
        user_ref = db.collection('users').document()
        user_ref.set({
            'password': password
        })
    
        return user_ref.id
    except Exception as e:
        print(f"Error creating user: {str(e)}")
        return None

def get_user(user_id: str):
    try:
        user_ref = db.collection('users').document(user_id)
        user_data = user_ref.get()
        if user_data.exists:
            return user_data.to_dict()
        else:
            return None
    except Exception as e:
        print(f"Error retrieving user: {str(e)}")
        return None

def create_library_item(user_id: str):
    try:
        library_ref = db.collection('library').document()
        library_ref.set({
            'user_id': user_id
        })
    
        return library_ref.id
    except Exception as e:
        print(f"Error creating library item: {str(e)}")
        return None

def get_library_items(user_id: str):
    try:
        library_items = db.collection('library').where('user_id', '==', user_id).stream()
        result = []
        for item in library_items:
            result.append(item.to_dict())
        return result
    except Exception as e:
        print(f"Error retrieving library items: {str(e)}")
        return []


def create_topic(topic: Topic) -> str:
    try:
        
        topic_ref = db.collection('topics').document()
        topic_data = topic.dict()
        topic_ref.set(topic_data)
        return topic_ref.id
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to create topic: {str(e)}")

def get_topic(topic_id: str) -> Topic:
    try:
        
        topic_ref = db.collection('topics').document(topic_id)
        topic_data = topic_ref.get()
        if topic_data.exists:
            return Topic(**topic_data.to_dict())
        else:
            raise HTTPException(status_code=404, detail="Topic not found")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to retrieve topic: {str(e)}")

def update_topic(topic_id: str, topic: Topic) -> Topic:
    try:
        
        topic_ref = db.collection('topics').document(topic_id)
        topic_data = topic.dict(exclude_unset=True)  
        topic_ref.update(topic_data)
        return get_topic(topic_id)
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to update topic: {str(e)}")

def delete_topic(topic_id: str):
    try:
        
        topic_ref = db.collection('topics').document(topic_id)
        topic_ref.delete()
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to delete topic: {str(e)}")
    
def create_card(card: Card) -> str:
    try:
        
        card_ref = db.collection('cards').document()
        card_data = card.dict()
        card_ref.set(card_data)
        return card_ref.id
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to create card: {str(e)}")


def get_cards_by_topic(topic_id: str) -> List[Card]:
    try:
        
        cards_ref = db.collection('cards').where('topic_id', '==', topic_id).stream()
        cards = []
        for card in cards_ref:
            card_data = card.to_dict()
            card_id = card.id  
            
            card_obj = Card(id=card_id, term=card_data['term'], meaning=card_data['meaning'], is_starred=card_data['is_starred'], rate=card_data['rate'], topic_id=card_data['topic_id'])
            cards.append(card_obj)
        return cards
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to retrieve cards: {str(e)}")


def get_card(card_id: str):
    try:
        
        card_ref = firestore.client().collection('cards').document(card_id)
        card_data = card_ref.get()
        if card_data.exists:
            
            return card_data.to_dict()
        else:
            
            raise HTTPException(status_code=404, detail="Card not found")
    except Exception as e:
        
        raise HTTPException(status_code=500, detail=f"Failed to retrieve card: {str(e)}")

def update_card(card_id: str, card: Card) -> Card:
    try:
        
        card_ref = db.collection('cards').document(card_id)
        card_data = card.dict(exclude_unset=True)  
        card_ref.update(card_data)
        return get_card(card_id)
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to update card: {str(e)}")

def delete_card(card_id: str):
    try:
        
        card_ref = db.collection('cards').document(card_id)
        card_ref.delete()
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to delete card: {str(e)}")
    

def generate_public_id(card_id: str) -> str:
    # Tạo một chuỗi ngẫu nhiên độ dài 8 ký tự
    random_string = ''.join(random.choices(string.ascii_lowercase + string.digits, k=8))
    # Kết hợp card_id và chuỗi ngẫu nhiên để tạo public_id duy nhất
    return f"{card_id}_{random_string}"

def upload_image(card_id: str, image_file: str) -> str:
    try:
        # Tạo public_id duy nhất cho ảnh
        public_id = generate_public_id(card_id)

        # Upload ảnh lên Cloudinary
        upload_result = cloudinary.uploader.upload(image_file, public_id=public_id)

        # Lưu URL của ảnh và card_id vào Firestore
        db.collection('card_images').document(public_id).set({
            'url': upload_result['secure_url'],
            'card_id': card_id
        })

        return upload_result['secure_url']
    except Exception as e:
        print(f"Failed to upload image and save info: {str(e)}")
        return ""
    
def download_image_by_card_id(card_id: str) -> str:
    try:
        # Query Firestore for image URL based on card_id
        image_ref = db.collection('card_images').where('card_id', '==', card_id).limit(1)
        image_data = image_ref.stream()

        # Get image URL and download using Cloudinary
        for image in image_data:
            image_url = image.to_dict()['url']
            return cloudinary.CloudinaryImage(image_url).build_url()
    except Exception as e:
        print(f"Failed to download image: {str(e)}")
        return ""
