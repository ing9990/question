FROM openjdk:11

USER root
RUN apt-get update
RUN curl -sSL https://get.docker.com/ | sh

WORKDIR /app
COPY . /app
RUN ./gradlew clean build