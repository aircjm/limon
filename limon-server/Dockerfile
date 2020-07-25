FROM openjdk:8u191-jdk-alpine3.9
EXPOSE 8085

#VOLUME /tmp
ADD target/limon-server.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=/data/java/limon/config/application-prod.yml"]
