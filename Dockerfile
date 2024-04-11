FROM openjdk:17-jdk-alpine AS build
WORKDIR /opt/app

COPY ./ /opt/app

FROM openjdk:17-jdk-alpine
COPY --from=build /opt/app/target/*.jar spring-technica-test.jar

EXPOSE 9100
CMD ["java", "-jar", "-Xms256m", "-Xmx1G", "spring-technica-test.jar"]