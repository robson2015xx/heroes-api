FROM openjdk:latest
EXPOSE 8080
ARG JAR_FILE=target/gubee-teste-pleno-gubee.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]