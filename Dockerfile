FROM openjdk:17-jdk-alpine

COPY /var/lib/jenkins/workspace/spring-technica-test/target/*.jar spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]