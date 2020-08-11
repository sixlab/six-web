#!/usr/bin/env bash

source /etc/profile

cmd=$(ps aux | grep six-web.jar | grep java | awk '{print $2}')

echo -e "PID: \n\033[31m\033[05m$cmd\033[0m"

echo "PID:$cmd"

for id in ${cmd}; do
  kill -9 ${id}
  echo "kill $id"
done
echo 'finish kill'

nohup java -jar six-web.jar --spring.profiles.active=prod >nohup.out 2>&1 &
