FROM openjdk-17 AS build
WORKDIR /app

COPY ./ /app
RUN mvn clean install -DskipTests

COPY target/spring-technica-test-0.0.1-SNAPSHOT.jar /app/spring-technica-test.jar

EXPOSE 9100
ENTRYPOINT ["java", "-jar", "-Xms256m", "-Xmx1G", "-Dserver.port=9100", "spring-technica-test.jar"]