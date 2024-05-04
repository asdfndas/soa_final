from pydantic import BaseModel, HttpUrl
from typing import List
from datetime import datetime

# Schema for User
class UserBase(BaseModel):
    username: str
    password: str

class UserCreate(UserBase):
    pass

class User(UserBase):
    id: int

    class Config:
        orm_mode = True


# Schema for Topic
class TopicBase(BaseModel):
    name: str
    description: str

class TopicCreate(TopicBase):
    pass

class Topic(TopicBase):
    id: int
    user_id: int
    cards: List['Card'] = []

    class Config:
        orm_mode = True


# Schema for Card
class CardBase(BaseModel):
    term: str
    meaning: str

class CardCreate(CardBase):
    pass

class Card(CardBase):
    id: int
    topic_id: int
    rate: float = 0.0
    is_starred: bool = False
    images: List['CardImage'] = []

    class Config:
        orm_mode = True


# Schema for CardImage
class CardImageBase(BaseModel):
    url: HttpUrl
    card_id: int

class CardImageCreate(CardImageBase):
    pass

class CardImage(CardImageBase):
    id: int
    card: Card

    class Config:
        orm_mode = True


# Schema for CardImageUpload
class CardImageUpload(BaseModel):
    card_id: int
    url: str

class CardImageResponse(BaseModel):
    url: str

# Schema for TypeTest
class TypeTestBase(BaseModel):
    name: str
    description: str

class TypeTestCreate(UserBase):
    pass

class TypeTest(UserBase):
    id: int

    class Config:
        orm_mode = True


# Schema cho TotalTest
class TotalTestBase(BaseModel):
    datetime: datetime
    point: float
    user_id: int
    type_id: int

class TotalTestCreate(TotalTestBase):
    pass

class TotalTest(TotalTestBase):
    id: int
    user: User
    type_of_test_id: int

    class Config:
        orm_mode = True 
