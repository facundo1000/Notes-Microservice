FROM openjdk:17
VOLUME /tmp
ADD target/note-service.jar note-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/note-service.jar"]