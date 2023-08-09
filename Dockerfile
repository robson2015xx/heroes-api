FROM openjdk:latest
ARG JAR_FILE=../../.././target/teste-pleno-gubee.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "app.jar""]