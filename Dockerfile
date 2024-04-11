FROM openjdk:17-jdk-alpine AS build
WORKDIR /app

COPY target/*.jar spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]