# Etapa 1: Build da aplicação com Maven e OpenJDK 19
FROM maven:3.9.6-openjdk-19 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final leve com JDK 19
FROM openjdk:19-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
