#!/bin/bash

BLUE_PORT=8081
GREEN_PORT=8082


BLUE_PID=$(lsof -i:${BLUE_PORT} -t )
GREEN_PID=$(lsof -i:${GREEN_PORT} -t )


RUNNING_ENV_PORT=$([[ -z ${BLUE_PID} ]] && echo ${GREEN_PORT} || echo ${BLUE_PORT})
PENDING_ENV_PORT=$([[ -z ${BLUE_PID} ]] && echo ${BLUE_PORT} || echo ${GREEN_PORT})


echo ${RUNNING_ENV_PORT}
echo ${PENDING_ENV_PORT}

#-Dspring.profiles.active=prod
nohup /usr/bin/java -jar  -Dserver.port=${PENDING_ENV_PORT} target/app.war >/dev/null 2>&1 &


while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:${PENDING_ENV_PORT})" != "200" ]]; do
sleep 5;
done

kill -9 ${BLUE_PID} || kill -9 ${GREEN_PID}


echo "App now running on port ${PENDING_ENV_PORT}"