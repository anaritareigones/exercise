# syntax=docker/dockerfile:1

FROM openjdk:11
ADD . /exercise
ARG JAR_FILE=target/*.jar
VOLUME /exercise/
WORKDIR /exercise/app
COPY ${JAR_FILE} app.jar
COPY sample.db /exercise/sample.db
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
