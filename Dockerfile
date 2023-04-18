#FROM openjdk:11
FROM adoptopenjdk:11

WORKDIR /app
COPY . /app

RUN ./gradlew test
RUN ./gradlew clean build

EXPOSE 80 8080

CMD ["java", "-jar", "app/build/libs/question-0.0.1.jar"]