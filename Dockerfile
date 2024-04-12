FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY target/spring-technica-test-0.0.1-SNAPSHOT.jar /app/spring-technica-test.jar
RUN addgroup -S spring && adduser -S appuser -G spring
RUN chown -R appuser:spring /app

USER appuser

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]