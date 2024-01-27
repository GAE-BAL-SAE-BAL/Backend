FROM openjdk:latest

COPY ./build/libs/*.jar /app.jar
COPY src/main/resources/bootsecurity.p12 /app/src/main/resources/

ENTRYPOINT ["java", "-jar", "/app.jar"]