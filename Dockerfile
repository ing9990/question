FROM adoptopenjdk:11

WORKDIR /app
COPY . /app

RUN ./gradlew clean build

EXPOSE 7878
CMD ["java", "-jar", "build/libs/question-0.0.1.jar"]