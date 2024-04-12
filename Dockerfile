FROM openjdk:17-jdk-alpine
WORKDIR /opt/app
COPY target/*.jar /opt/app/spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]