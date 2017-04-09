from flask import Flask, request, send_from_directory
from core.config import get_conf
from core.db import DbLocal
from core.check import Check
from flask_cors import CORS, cross_origin
from core.log import *
import json
import time
import hashlib

def set_hash():
    hash = hashlib.md5()
    hash.update(str(time.time()).encode('utf-8'))
    return hash.hexdigest()

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


app = Flask(__name__)
CORS(app)
ALLOWED_EXTENSIONS = ('c', 'py', 'rb', 'cpp', 'sh')


@app.route('/upload', methods=['GET', 'POST'])
def upload_file():
    url_base = "http://0.0.0.0:5001/upload?id="
    if request.method == 'POST':
        data = request.args.to_dict()
        data_json = json.loads(json.dumps(data)) # what?!
        try:
            if data_json['flag'] and data_json['file'] and data_json['filename'] and data_json['address'] and data_json['interface']:
                address = data_json['address']
                interface = data_json['interface']
                flag = data_json['flag']
                file = data_json['file']
                filename = data_json['filename']

                if filename == '':
                    return json.dumps({"Error": "No selected files"})

                if allowed_file(filename):
                    hash_value = set_hash()
                    create_file = open(config['web']['UPLOAD'] + "/" + hash_value, "w")
                    create_file.write(file)
                    create_file.close()

                    c = Check(hash_value, flag)
                    c.docker_run()
                    status = c.exploit_run()
                    c.exit()

                    state = json.dumps({"Message": "Uploaded", "Status": status, "URL": url_base + hash_value})
                    sql = "INSERT INTO status(hash, state, interface, address) VALUES ('{0}','{1}'," \
                          "'{2}','{3}')".format(hash_value,json.dumps({"Status": status, "URL": url_base + hash_value, "Message": "Uploaded"}),
                                               interface, address)
                    db.query(sql)
                    return state
                else:
                    return json.dumps({"Error": "Bad format file"})
        except:
            return json.dumps({"Error": "Not all fields are filled"})

    elif request.method == 'GET':
        req = request.args.to_dict()
        if req['id']:
            sql = "SELECT state FROM status WHERE hash = ('{0}')".format(req['id'])
            print(sql)
            db.query(sql)

            try:
                row = db.fetch()[0]
                return json.dumps(row, send_from_directory(directory=config['web']['UPLOAD'], filename=req['id'], as_attachment=True))
            except:
                return json.dumps({"Error": "File does not exist"})


if __name__ == "__main__":
    config = get_conf()['conf']
    db = DbLocal()
    app.run(host=config['web']['IP'], port=config['web']['PORT'])

