FROM openjdk:17-alpine

ADD target/spring-technica-test-0.0.1-SNAPSHOT.jar /app/spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]