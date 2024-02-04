FROM eclipse-temurin:17-jdk-alpine
LABEL authors="nipundewenarayane"
ADD build/libs/squiz-0.0.1-SNAPSHOT.jar squiz.jar
ENTRYPOINT ["java", "-jar", "squiz.jar"]