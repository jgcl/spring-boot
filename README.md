# Spring Boot 2.x with SpringDoc, Lombok and MongoDB

## Solution

- Java 8+
- Spring Boot 2.2.6
- SpringDoc 1.3.4
- Lombok
- Mongo 4.2.6

## Run

The application will run on port 8080 and de Swagger is present in http://localhost:8080/swagger-ui.html

## Run using Java 8+

It is necessary to inform a MongoDB database, in "docker-compose.yml" I already contemplate one:
```bash
docker-compose up -d mongo
```

Now just run the application:
```bash
mvn package --batch-mode
java -jar target/application-latest.jar
```

Or use another address from the MongoDB database:
```bash
mvn package --batch-mode
java -Dspring.data.mongodb.uri="mongodb://root:example@localhost:27017/spring-boot?authSource=admin&readPreference=primary" -jar target/application-latest.jar
```

## Run using Docker
```bash
docker-compose pull
docker-compose up -d
```