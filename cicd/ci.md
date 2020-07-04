mkdir /home/api/prod
mkdir /home/api/stage


cp fx-prod.service /etc/systemd/system/
cp fx-stage.service /etc/systemd/system/



to do /etc/sudoers

ptheis ALL=(ALL) NOPASSWD: /usr/bin/systemctl status fx-stage
ptheis ALL=(ALL) NOPASSWD: /usr/bin/systemctl restart fx-stage

ptheis ALL=(ALL) NOPASSWD: /usr/bin/systemctl status fx-prod
ptheis ALL=(ALL) NOPASSWD: /usr/bin/systemctl restart fx-prod
