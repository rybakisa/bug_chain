#!/usr/bin/python
# -*- coding: utf-8 -*-

from flask import Flask, request, send_from_directory
from Core.config import get_conf
import json
import time
import hashlib

ALLOWED_EXTENSIONS = ('c', 'py', 'rb', 'cpp', 'sh')

def set_hash():
    hash = hashlib.md5()
    hash.update(str(time.time()).encode('utf-8'))
    return hash.hexdigest()


def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


app = Flask(__name__)
@app.route('/upload', methods=['GET', 'POST'])
def upload_file():

    url_base = "http://0.0.0.0:5001/upload/"
    if request.method == 'POST':
        if 'file' not in request.files:
            return json.dumps({"Error": "No file part"})

        file = request.files['file']
        if file.filename == '':
            return json.dumps({"Error": "No selected files"})

        elif file and allowed_file(file.filename):
            hash_value = set_hash()
            file.save(config['web']['UPLOAD_FOLDER'] + "/" + hash_value)
            return json.dumps({"Status": "Upload", "URL": url_base + hash_value})

        else:
            return json.dumps({"Error": "Bad format file"})


    elif request.method == 'GET':
        req = request.args.to_dict()
        if req['id']:
            try:
                return send_from_directory(directory=config['web']['UPLOAD_FOLDER'],
                                           filename=req['id'],
                                           as_attachment=True)
            except:
                return json.dumps({"Error": "File does not exist"})


if __name__ == '__main__':
    config = get_conf()['conf']
    app.run(host=config['web']['IP'], port=config['web']['PORT'])
