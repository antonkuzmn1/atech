#!/bin/bash

read -p "[UPLOAD] Enter the version number: " version

# ssh root@192.168.250.140 "pkill -f /usr/local/www/backend-0.0.$version-SNAPSHOT.jar"
scp /home/anton/git/atech/reg/backend/build/libs/backend-0.0.$version-SNAPSHOT.jar root@192.168.250.140:/usr/local/www
ssh root@192.168.250.140 "nohup java -jar /usr/local/www/backend-0.0.$version-SNAPSHOT.jar > /usr/local/www/spring.log 2>&1 &"