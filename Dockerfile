FROM openjdk:17-jdk-alpine

COPY target/spring-technica-test-0.0.1-SNAPSHOT.jar spring-technica-test.jar
RUN addgroup -S spring && adduser -S appuser -G spring
RUN chown -R appuser:spring /app

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]