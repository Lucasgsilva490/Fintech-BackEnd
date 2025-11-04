# Etapa 1: Build da aplicação com Maven
FROM maven:3.9.9-openjdk-19 AS build
WORKDIR /app

# Copia o pom.xml e as dependências primeiro (para cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código e compila
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final leve com apenas o JAR
FROM openjdk:19-jdk-slim
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/wallets-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a API
ENTRYPOINT ["java", "-jar", "app.jar"]
