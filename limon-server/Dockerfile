FROM openjdk:8u191-jdk-alpine3.9
EXPOSE 8085

#VOLUME /tmp
ADD target/limon-server.jar app.jar
ENV JAVA_OPTS="-Xms256m -Xmx256m"

ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -server -jar app.jar