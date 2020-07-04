#!/bin/bash

../mvnw clean package -DskipTests=true -Pprod
#scp /home/piotr/www-java/citi/target/citi.war  piotrtheis@s3.escl24.pl:/home/piotrtheis

scp -o PreferredAuthentications=password -o PubkeyAuthentication=no /home/piotr/www-java/pulsrynkufx2.0/target/fx.war  ptheis@51.75.56.242:/home/ptheis


# XL9MGBAmaG

#  ./mvnw spring-boot:run -Drun.profiles=dev



#ssh-copy-id -i /home/piotr/.ssh/fx_stage -o PreferredAuthentications=password -o PubkeyAuthentication=no ptheis@51.75.56.242