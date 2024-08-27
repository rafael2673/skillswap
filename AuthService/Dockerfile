# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

COPY target/*.jar /app/app.jar

EXPOSE 8081
ENV TZ=GMT-3
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

