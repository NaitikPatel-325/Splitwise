FROM maven:3.8.7-eclipse-temurin-17 AS build
COPY . .

ENV JAVA_HOME=/opt/java/openjdk

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /app/target/Splitwise-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
