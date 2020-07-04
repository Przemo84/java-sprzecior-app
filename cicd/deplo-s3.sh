#!/bin/bash

../mvnw clean package -DskipTests=true -Ps3

scp -o PreferredAuthentications=password -o PubkeyAuthentication=no ./target/citi.war  ptheis@cititom.escl24.pl:/home/ptheis