# Build stage
FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean test package -DskipTests

# Package stage
FROM azul/zulu-openjdk-alpine:21
COPY --from=build /home/app/target/*.jar hackaton-pagamentos-app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","hackaton-pagamentos-app.jar"]