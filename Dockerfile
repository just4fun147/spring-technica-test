FROM openjdk:17-alpine
WORKDIR /opt/app

COPY /opt/apptarget/spring-technica-test-0.0.1-SNAPSHOT.jar spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]