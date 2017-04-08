import yaml
import os
import sys


class Config:
    """Operation yaml config."""
    def __init__(self):
        if os.path.isfile('conf/conf.yaml'):
            self.__configYaml = yaml.load(open('conf/conf.yaml'))
        else:
            print("ERROR: file conf/conf.yaml not found")
            sys.exit()

    def get_conf(self):
        """Return dict data, from conf.yaml"""
        return self.__configYaml


def get_conf():
    obj_conf = Config()
    return obj_conf.get_conf()