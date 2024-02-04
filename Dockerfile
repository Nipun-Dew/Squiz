FROM gradle:4.7.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jre-slim

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar squiz.jar

ENTRYPOINT ["java", "-jar", "squiz.jar"]