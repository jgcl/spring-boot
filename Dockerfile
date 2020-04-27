FROM maven:3.6.1-jdk-11-slim
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn package --batch-mode

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=0 /app/target/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]