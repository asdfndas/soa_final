from sqlalchemy import Column, ForeignKey, Integer, String, Boolean, Float, DateTime
from sqlalchemy.orm import relationship
from database import Base

class User(Base):
    __tablename__ = 'users'

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String, unique=True, index=True)
    password = Column(String)
    


class Topic(Base):
    __tablename__ = 'topics'

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String)
    description = Column(String)
    user_id = Column(Integer, ForeignKey('users.id'))

    user = relationship('User')
    cards = relationship('Card', back_populates='topic')
    test_type = relationship('TypeOfTest', back_populates='topic')

class Card(Base):
    __tablename__ = 'cards'

    id = Column(Integer, primary_key=True, index=True)
    term = Column(String)
    meaning = Column(String)
    is_starred = Column(Boolean, default=False)
    rate = Column(Float)
    topic_id = Column(Integer, ForeignKey('topics.id'))

    topic = relationship('Topic', back_populates='cards')
    images = relationship('CardImage', back_populates='card')

class CardImage(Base):
    __tablename__ = 'card_images'

    id = Column(Integer, primary_key=True, index=True)
    url = Column(String)
    card_id = Column(Integer, ForeignKey('cards.id'))

    card = relationship('Card', back_populates='images')

class TypeOfTest(Base):
    __tablename__ = 'type_of_test'

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String)
    topic_id = Column(Integer, ForeignKey('topics.id'))

    topic = relationship('Topic')

class TotalTest(Base):
    __tablename__ = 'total_tests'

    id = Column(Integer, primary_key=True, index=True)
    datetime = Column(DateTime)
    point = Column(Float)
    user_id = Column(Integer, ForeignKey('users.id'))
    type_id = Column(Integer, ForeignKey('type_of_test.id'))

    user = relationship('User')
    type_of_test = relationship('TypeOfTest')
