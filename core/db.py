from core.config import get_conf
from core.log import log
import psycopg2
import sys

class DbLocal():
    def __init__(self):
        conf = get_conf()['conf']

        auth_str = "host='{0}' port='{1}' dbname='{2}' user='{3}' password='{4}'".format(
            conf['db']['PG_HOST'],
            conf['db']['PG_PORT'],
            conf['db']['PG_DB'],
            conf['db']['PG_USER'],
            conf['db']['PG_PWD'])

        try:
            self.db_conn = psycopg2.connect(auth_str)
            self.db_cursor = self.db_conn.cursor()
        except:
            log.error("Error connecting to the database.")
            sys.exit(0)

    def query(self, sql):
        """ execute query """
        self.db_cursor.execute(sql)
        self.db_conn.commit()

    def fetch(self):
        return self.db_cursor.fetchone()

    def to_binary(self, data):
        return (psycopg2.Binary(data),)

    def close(self):
        if self.db_conn:
            self.db_conn.close()

        if self.db_cursor:
            self.db_cursor.close()

    def __exit__(self, exc_type, exc_val, exc_tb):

        if self.db_conn:
            self.db_conn.close()

        if self.db_cursor:
            self.db_cursor.close()

