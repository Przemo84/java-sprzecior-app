FROM anapsix/docker-oracle-java8

# Install maven
RUN apt-get update -y
RUN apt-get install -y maven

# Creating working directory
WORKDIR /application

# Add src to working directory
ADD pom.xml /application/pom.xml
ADD src /application/src
ADD upload-dir /application/upload-dir
ADD start.sh /application/start.sh

RUN ls -al
RUN cat /etc/hosts

# Build JAR
RUN mvn package -DskipTests=true

EXPOSE 8080

# Start app
#ENTRYPOINT ["java","-jar","demo.jar"]
#ENTRYPOINT ["mvn","spring-boot:run", "-DskipTests=true"]
