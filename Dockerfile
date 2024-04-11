FROM maven:3-jdk-17-alpine AS build
WORKDIR /opt/app

COPY ./ /opt/app
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine
COPY /opt/app/target/spring-technica-test-0.0.1-SNAPSHOT.jar spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]