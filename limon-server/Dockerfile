FROM java:8
EXPOSE 8900

#VOLUME /tmp
ADD limon-server.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=/data/java/config/application-prod.yml"]
