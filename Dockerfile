FROM openjdk:17-jdk-alpine

EXPOSE 9100
ADD target/*.jar spring-technica-test.jar

CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "/spring-technica-test.jar"]