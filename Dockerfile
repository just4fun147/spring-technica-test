FROM openjdk:17-jdk-alpine
WORKDIR /var/lib/jenkins/workspace/spring-technica-test
COPY target/spring-technica-test.jar spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]