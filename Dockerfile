# Imagem base com Java 17 (compatível com Spring Boot 2.7)
FROM eclipse-temurin:17-jre-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado pelo Maven para dentro do container
COPY target/nowait-ms1-cardapio-1.0.0-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Ponto de entrada
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
