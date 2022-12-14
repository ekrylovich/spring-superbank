FROM openjdk:11-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 9090
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]