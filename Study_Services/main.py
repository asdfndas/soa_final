from fastapi import FastAPI, HTTPException, File, UploadFile
from firebase_utils import *
from pydantic import BaseModel
from typing import List
from models import *
import cloudinary
import cloudinary.uploader
from cloudinary_config import *

app = FastAPI()


@app.post("/users/")
def api_create_user(user: User):
    user_id = create_user(user.password)
    if user_id:
        return {"message": "User created successfully", "user_id": user_id}
    else:
        raise HTTPException(status_code=500, detail="Failed to create user")

@app.get("/users/{user_id}")
def api_get_user(user_id: str):
    user_data = get_user(user_id)
    if user_data:
        return user_data
    else:
        raise HTTPException(status_code=404, detail="User not found")

@app.post("/library/")
def api_create_library_item(library: Library):
    library_item_id = create_library_item(library.user_id)
    if library_item_id:
        return {"message": "Library item created successfully", "library_item_id": library_item_id}
    else:
        raise HTTPException(status_code=500, detail="Failed to create library item")

@app.get("/library/{user_id}")
def api_get_library_items(user_id: str):
    library_items = get_library_items(user_id)
    return library_items

@app.post("/topics/", response_model=str)
def api_create_topic(topic: Topic):
    return create_topic(topic)

@app.get("/topics/{topic_id}", response_model=Topic)
def api_get_topic(topic_id: str):
    return get_topic(topic_id)

@app.put("/topics/{topic_id}", response_model=Topic)
def api_update_topic(topic_id: str, topic: Topic):
    return update_topic(topic_id, topic)

@app.delete("/topics/{topic_id}")
def api_delete_topic(topic_id: str):
    delete_topic(topic_id)
    return {"message": "Topic deleted successfully"}

@app.post("/cards/", response_model=str)
def api_create_card(card: Card):
    return create_card(card)

@app.get("/cards/{topic_id}", response_model=List[Card])
def api_get_cards_by_topic(topic_id: str):
    return get_cards_by_topic(topic_id)

@app.put("/cards/{card_id}", response_model=Card)
def api_update_card(card_id: str, card: Card):
    return update_card(card_id, card)

@app.delete("/cards/{card_id}")
def api_delete_card(card_id: str):
    delete_card(card_id)
    return {"message": "Card deleted successfully"}


@app.post("/upload")
async def upload_image(card_id: str, file: UploadFile = File(...)):
    # Tạo public_id từ card_id và chuỗi ngẫu nhiên
    random_string = ''.join(random.choices(string.ascii_lowercase + string.digits, k=6))
    public_id = f"{card_id}-{random_string}"

    # Upload file lên Cloudinary
    upload_result = cloudinary.uploader.upload(file.file, public_id=public_id)

    # Trả về kết quả upload
    return {"url": upload_result['secure_url']}