#!/usr/bin/python3.5

import logging.config
import sys

logging.config.fileConfig(sys.path[0] + '/conf/logger.conf')
log = logging.getLogger('')
