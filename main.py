import os

import pyrebase
from flask import Flask, render_template, session, request, redirect, flash
from werkzeug.utils import secure_filename


app = Flask(__name__)
config = {

    "apiKey": "AIzaSyAtbO4GaMUa5TqAozkeDksWkWQHjuXV6e4",

    "authDomain": "wayapp-a6d5d.firebaseapp.com",

    "databaseURL": "https://wayapp-a6d5d-default-rtdb.europe-west1.firebasedatabase.app",

    "projectId": "wayapp-a6d5d",

    "storageBucket": "wayapp-a6d5d.appspot.com",

    "messagingSenderId": "1066558457563",

    "appId": "1:1066558457563:web:1bb062025ae32ef9706efe",

    "measurementId": "G-JER5R0Z8S4",
    'serviceAccount': 'static/wayapp-a6d5d-firebase-adminsdk-v0ycz-17465c1c96.json'

}

firebase = pyrebase.initialize_app(config)
auth = firebase.auth()
app.config['SECRET_KEY'] = 'secret!'

storage = firebase.storage()

bucket = storage.bucket


# bucket = storage.bucket()
def upload_file(filename, file):
    bucket.blob(filename).upload_from_filename('firebase/' + filename)


for file in os.listdir('firebase'):
    upload_file(file, 'firebase/' + file)