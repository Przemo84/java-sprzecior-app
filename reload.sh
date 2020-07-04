#!/bin/bash
# XL9MGBAmaG

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
lsof -i:8080 -t | xargs kill -9

nohup ./mvnw spring-boot:run -Drun.profiles=stage &