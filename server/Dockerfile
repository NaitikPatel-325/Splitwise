FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

ENV JAVA_HOME=/opt/java/openjdk

COPY pom.xml .
COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

ENV JAVA_HOME=/opt/java/openjdk

COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
