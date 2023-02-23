FROM openjdk:17
VOLUME /tmp
ADD target/note-service.jar note-service.jar
ENTRYPOINT ["java", "-jar","/note-service.jar"]