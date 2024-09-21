FROM eclipse-temurin:17-jdk-jammy

RUN groupadd -g 1000 mygroup && \
    useradd -u 1000 -g mygroup -m myuser

ARG JAR_FILE=target/*.jar

COPY --chown=myuser:mygroup ${JAR_FILE} /app.jar

USER myuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
