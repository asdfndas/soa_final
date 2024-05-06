from sqlalchemy.orm import Session
from models import User, Topic, Card, CardImage
from fastapi import HTTPException, File, UploadFile
from typing import List, Optional
from sqlalchemy.orm import Session
from sqlalchemy.exc import IntegrityError
import models, schemas
from random import sample
import cloudinary
import cloudinary.uploader
from cloudinary_config import *
import random
import string

def create_user(db: Session,user: User) -> User:
    try:
        db_user = models.User(**user.dict())
        db.add(db_user)
        db.commit()
        db.refresh(db_user)
        return db_user
    except IntegrityError:
        raise ValueError("Username already exists")

def get_user(db: Session, user_id: int) -> Optional[User]:
    return db.query(User).filter(User.id == user_id).first()

def create_topic(db: Session, topic: schemas.TopicCreate, user_id: int):
    user = db.query(models.User).filter(models.User.id == user_id).first()
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    db_topic = models.Topic(name=topic.name, description=topic.description, user_id=user_id)
    db.add(db_topic)
    db.commit()
    db.refresh(db_topic)
    return db_topic

def get_topic(db: Session, topic_id: int) -> models.Topic:
    topic = db.query(models.Topic).filter(models.Topic.id == topic_id).first() 
    if not topic:
         raise HTTPException(status_code=404, detail="Topic not found")
    return topic

def get_topic_by_user(db: Session, user_id: int):
    return db.query(models.Topic).filter(models.Topic.user_id == user_id).all()

def update_topic(db: Session, topic_id: int, topic: schemas.TopicUpdate):
    db_topic = db.query(models.Topic).filter(models.Topic.id == topic_id).first()
    if db_topic:
        db_topic.name = topic.name
        db_topic.description = topic.description
        db.commit()
        db.refresh(db_topic)
    return db_topic

def delete_topic(db: Session, topic_id: int):
    db_topic = db.query(models.Topic).filter(models.Topic.id == topic_id).first()
    
    if db_topic:
        db_cards = db.query(models.Card).filter(models.Card.topic_id == topic_id).all()
        for card in db_cards:
            card_id = card.id
            db_images = db.query(models.CardImage).filter(models.CardImage.card_id == card_id).all()
            for img in db_images:
                db.delete(img)
            db.delete(card)

    db.delete(db_topic)
    db.commit()
    
    return db_topic
        # except Exception as e:
        #     db.rollback()
        #     raise e
    
    return False

def create_card(db: Session, card: schemas.CardCreate, topic_id: int):
    topic = db.query(models.Topic).filter(models.Topic.id == topic_id).first()
    if not topic:
        raise HTTPException(status_code=404, detail="Topic not found")
    db_card = models.Card(
        term=card.term,
        meaning=card.meaning,
        is_starred=False,
        rate=0.0,
        topic_id=topic_id
    )
    db.add(db_card)
    db.commit()
    db.refresh(db_card)
    return db_card

def get_card(db: Session, card_id: int):
    card = db.query(models.Card).filter(models.Card.id == card_id).first() 
    if not card:
         raise HTTPException(status_code=404, detail="Card not found")
    return card

def set_starred_card(db: Session, card_id: str, value: bool):
    card = db.query(models.Card).filter(models.Card.id == card_id).first() 
    if not card:
         raise HTTPException(status_code=404, detail="Card not found")
    card.is_starred = value
    db.commit()
    db.refresh(card)
    return card

def setup_rate_card(db: Session, card_id: int):
    card = db.query(models.Card).filter(models.Card.id == card_id).first()
    if not card:
        raise HTTPException(status_code=404, detail="Card not found")
    card.rate = min(card.rate + 1, 5)
    db.commit()
    db.refresh(card)
    return card

def setdown_rate_card(db: Session, card_id: int):
    card = db.query(models.Card).filter(models.Card.id == card_id).first()
    if not card:
        raise HTTPException(status_code=404, detail="Card not found")
    card.rate = max(card.rate - 1, 1)
    db.commit()
    db.refresh(card)
    return card

def update_card(db: Session, card_id: int, card: schemas.CardUpdate):
    db_card = db.query(models.Card).filter(models.Card.id == card_id).first()
    if db_card:
        db_card.term = card.term
        db_card.meaning = card.meaning
        db.commit()
        db.refresh(db_card)
    return db_card

def delete_card(db: Session, card_id: int):
    db_card = db.query(models.Card).filter(models.Card.id == card_id).first()
    if db_card:
        db.delete(db_card)
        db.commit()
        return db_card
    return False

def upload_image(db: Session, card_id: int, file: UploadFile) -> models.CardImage:
    card = db.query(models.Card).filter(models.Card.id == card_id).first()
    if not card:
        raise HTTPException(status_code=404, detail="Card not found")

    random_string = ''.join(random.choices(string.ascii_lowercase + string.digits, k=6))
    public_id = f"card-{card_id}-{random_string}"

    try:
        upload_result = cloudinary.uploader.upload(file.file, public_id=public_id)
        new_image = models.CardImage(url=upload_result['secure_url'], card_id=card_id)
        db.add(new_image)
        db.commit()
        db.refresh(new_image)

        return new_image

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Failed to upload image: {str(e)}")


def create_quiz_by_topic(db: Session, topic_id: int):
    cards = db.query(models.Card).filter(models.Card.topic_id == topic_id).all()

    quiz_questions = []

    for card in cards:
        num_wrong_answers = min(len(cards) - 1, 3) 
        wrong_answers = []

        while len(wrong_answers) < num_wrong_answers:
            random_card = random.choice(cards)
            if random_card.meaning != card.meaning and random_card.meaning not in [choice.value for choice in wrong_answers]:
                wrong_answers.append(schemas.Choice(value=random_card.meaning))

        question = schemas.Question(
            card_id = card.id,
            question_text = card.term,
            correct_answer = schemas.Choice(value=card.meaning),
            incorrect_answers=wrong_answers
        )
        quiz_questions.append(question)

    return quiz_questions

def create_total_test(db: Session, total_test_create: schemas.TotalTestCreate):
    datetime = total_test_create.datetime
    point = total_test_create.point
    user_id = total_test_create.user_id
    type_id = total_test_create.type_id
    
    new_total_test = models.TotalTest(
        datetime=datetime,
        point=point,
        user_id=user_id,
        type_id=type_id
    )
    
    db.add(new_total_test)
    db.commit()
    db.refresh(new_total_test)
    return new_total_test


def create_type_test(db: Session, type_test_create: schemas.TypeTestCreate):
    db_type_test = models.TypeOfTest(name=type_test_create.name, description=type_test_create.description, topic_id=type_test_create.topic_id)
    db.add(db_type_test)
    db.commit()
    db.refresh(db_type_test)
    return db_type_test