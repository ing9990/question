#FROM openjdk:11
FROM adoptopenjdk:11

WORKDIR /app
COPY . /app
EXPOSE 80 8080
RUN ./gradlew test
#RUN ./gradlew sonar
#RUN ./gradlew clean build
CMD ["nohup","java", "-jar", "question-0.0.1.jar"]