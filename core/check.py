from core.config import get_conf
from core.log import *
import subprocess
import docker
import os
import time

class Check():
    def __init__(self, exploit):
        conf = get_conf()['conf']
        self.ports = { conf['docker']['PORT_TO']: conf['docker']['PORT_FROM']}
        self.name = conf['docker']['NAME']
        self.exploit = conf['docker']['UPLOAD'] + exploit

    def docker_run(self):
        client = docker.from_env()
        try:
            self.container = client.containers.run(self.name, ports=self.ports, detach=True)
            time.sleep(2)
        except:
            log.error("Docker does not run")

    def exploit_run(self):
        os.chmod(self.exploit, 0o755)
        process = subprocess.Popen([self.exploit], stdout=subprocess.PIPE)
        process.wait()
        value = process.stdout.read().decode('utf-8')
        print(value)
        ## Add compare value with true_value

    def exit(self):
        self.container.kill()