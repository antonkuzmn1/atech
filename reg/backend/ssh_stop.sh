#!/bin/bash

read -p "[STOP] Enter the version number: " version

ssh root@192.168.250.140 "pkill -f /usr/local/www/backend-0.0.$version-SNAPSHOT.jar"