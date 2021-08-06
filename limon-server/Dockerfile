# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS builder

ENV APP_HOME=/usr/src/app
RUN mkdir -p $APP_HOME
COPY . $APP_HOME
WORKDIR $APP_HOME
RUN ls -la
RUN pwd
# package jar
RUN mvn package -Dmaven.test.skip=true
RUN pwd
RUN cd $APP_HOME/limon-web/target/
RUN ls


# Second stage: minimal runtime environment
From openjdk:8-jdk-alpine

# copy jar from the first stage
COPY --from=builder  /usr/src/app/limon-web/target/limon-web-1.0.0.jar app.jar

RUN ls la

EXPOSE 8085

ENV JAVA_OPTS="-Xms256m -Xmx256m"

#run the spring boot application
CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-server", "-jar","app.jar"]