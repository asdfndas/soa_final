from typing import List
from models import *
from sqlalchemy.exc import IntegrityError

from fastapi import FastAPI, Depends, HTTPException, File, UploadFile, Response, status
from fastapi.security import HTTPBearer

from sqlalchemy.orm import Session
import crud, models, schemas
from database import SessionLocal, engine

from utils import VerifyToken

Base.metadata.create_all(bind=engine)

app = FastAPI()
token_auth_scheme = HTTPBearer() 

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

#Test Jwt 
# @app.get("/api/private")
# def private(response: Response, token: str = Depends(token_auth_scheme)):

#     # result = VerifyToken(token.).verify

#     if result.get("status"):
#         response.status_code = status.HTTP_400_BAD_REQUEST
    
#         return result
    

#     return "message: Test JWT"


@app.post("/users/", response_model=schemas.User)
def create_user(user: schemas.User, db: Session = Depends(get_db)):
    try:
        return crud.create_user(db, user)
    except IntegrityError:
        raise HTTPException(status_code=400, detail="Username already exists")

@app.get("/users/{user_id}", response_model=schemas.User)
def read_user(user_id: int, db: Session = Depends(get_db)):
    user = crud.get_user(db, user_id)
    if user is None:
        raise HTTPException(status_code=404, detail="User not found")
    return user

@app.post("/topics/", response_model=schemas.Topic)
def create_topic(topic: schemas.TopicCreate, user_id: int, db: Session = Depends(get_db)):
    return crud.create_topic(db, topic=topic, user_id=user_id)

@app.get("/topics/{topic_id}", response_model=schemas.Topic)
def read_topic(topic_id: int, db: Session = Depends(get_db)):
    topic = crud.get_topic(db, topic_id)
    if topic is None:
        raise HTTPException(status_code=404, detail="Topic not found")
    return topic

@app.get("/topics_by_user/{user_id}")
def read_topic(user_id: int, db: Session = Depends(get_db)):
    topics = crud.get_topic_by_user(db, user_id)
    if topics is None:
        raise HTTPException(status_code=404, detail="Topic not found")
    return topics

@app.put("/topics/{topic_id}", response_model=schemas.Topic)
def update_topic(topic_id: int, topic: schemas.Topic, db: Session = Depends(get_db)):
    updated_topic = crud.update_topic(db, topic_id=topic_id, topic=topic)
    if updated_topic is None:
        raise HTTPException(status_code=404, detail="Topic not found")
    return updated_topic

@app.delete("/topics/{topic_id}")
def delete_topic(topic_id: int, db: Session = Depends(get_db)):
    topic = crud.delete_topic(db, topic_id)
    if not topic:
        raise HTTPException(status_code=404, detail="Topic not found")
    return topic

@app.post("/cards/", response_model=schemas.Card)
def create_card(card: schemas.CardCreate, topic_id: int, db: Session = Depends(get_db)):
    return crud.create_card(db, card, topic_id)

@app.get("/cards/{card_id}", response_model=schemas.Card)
def read_card(card_id: str, db: Session = Depends(get_db)):
    card = crud.get_card(db=db, card_id=card_id)
    if card is None:
        raise HTTPException(status_code=404, detail="Card not found")
    return card

@app.put("/cards/{card_id}", response_model=schemas.Card)
def update_card(card_id: str, card: schemas.Card, db: Session = Depends(get_db)):
    updated_card = crud.update_card(db=db, card_id=card_id, card=card)
    if updated_card is None:
        raise HTTPException(status_code=404, detail="Card not found")
    return updated_card

@app.delete("/cards/{card_id}")
def delete_card(card_id: str, db: Session = Depends(get_db)):
    if not crud.delete_card(db=db, card_id=card_id):
        raise HTTPException(status_code=404, detail="Card not found")
    return {"message": "Card deleted successfully"}


@app.post("/upload", response_model=schemas.CardImageResponse)
async def upload_image(card_id: int, file: UploadFile = File(...), db: Session = Depends(get_db)):
    return crud.upload_image(db=db, card_id=card_id, file=file)

