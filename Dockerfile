FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN ./gradlew clean build --stacktrace
ENTRYPOINT ["./gradlew", "bootRun"]