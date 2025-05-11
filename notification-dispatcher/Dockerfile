FROM eclipse-temurin:21-jdk
LABEL authors="oxymetholone"

COPY build/libs/notification-dispatcher-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]