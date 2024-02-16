#!/bin/ash
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY ./pom.xml /app/pom.xml
COPY ./src /app/src
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/email-service-0.0.1-SNAPSHOT.jar /app/app.jar
COPY ./wait-for-it.sh .
RUN chmod +x /app/wait-for-it.sh
EXPOSE 8086
CMD ["./wait-for-it.sh", "rabbitmq:5672", "--", "java", "-jar", "/app/app.jar"]
