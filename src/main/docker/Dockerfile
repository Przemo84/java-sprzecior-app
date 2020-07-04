FROM java:8

# Creating working directory
WORKDIR /app

VOLUME /tmp

ADD demo.jar demo.jar
RUN bash -c 'touch /demo.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/citi.war"]