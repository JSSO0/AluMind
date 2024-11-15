FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
ENV LANG=C.UTF-8
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
