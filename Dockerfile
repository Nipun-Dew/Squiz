FROM gradle:jdk17-focal AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:17
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar squiz.jar
ENTRYPOINT ["java", "-jar", "squiz.jar"]