FROM maven:3.8.2 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/gavrilenko-test-task-1.0-SNAPSHOT.jar .

CMD ["java", "-jar", "gavrilenko-test-task-1.0-SNAPSHOT.jar"]
