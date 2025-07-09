# Etapa 1: construir el .jar
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/apiexamen-*.jar app.jar
COPY wait-for-mysql.sh wait-for-mysql.sh
RUN chmod +x wait-for-mysql.sh && apt-get update && apt-get install -y netcat
EXPOSE 8080
ENTRYPOINT ["./wait-for-mysql.sh"]
