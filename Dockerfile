FROM adoptopenjdk/openjdk11:latest
COPY target/mytodo-1.0-SNAPSHOT.jar target/mytodo-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "target/mytodo-1.0-SNAPSHOT.jar"]