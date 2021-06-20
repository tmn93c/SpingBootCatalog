FROM adoptopenjdk/openjdk11:ubi
# FROM maven:alpine
ARG JAR_FILE=target/*.jar
# RUN mvn clean install -DskipTests
EXPOSE 8080
ADD catalog-1.0.jar catalog-1.0.jar
ENTRYPOINT ["java","-jar","catalog-1.0.jar"]