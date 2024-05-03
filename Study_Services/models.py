from pydantic import BaseModel
from fastapi import UploadFile

class User(BaseModel):
    password: str

class Library(BaseModel):
    user_id: str

class Topic(BaseModel):
    name: str
    description: str
    user_id: str

class Card(BaseModel):
    id: str
    term: str
    meaning: str
    is_starred: bool = False
    rate: float
    topic_id: str

class CardImage(BaseModel):
    id: str
    url: str
    card_id: str

class CardImageUpload(BaseModel):
    card_id: str
    image_file: UploadFile
