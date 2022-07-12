FROM adoptopenjdk/openjdk11:latest
COPY pvr-waermesimulierung-fatjar.jar pvr-waermesimulierung-fatjar.jar
CMD ["java", "-jar", "pvr-waermesimulierung-fatjar.jar"]
