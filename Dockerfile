# Imagem base com Java 17 (compatível com Spring Boot 2.7)
FROM eclipse-temurin:17-jre-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado pelo Maven para dentro do container
COPY target/mesa-certa-1.0-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (onde o Spring Boot sobe)
EXPOSE 8080

# Ponto de entrada: roda o jar
# OBS: Configuração externa (SPRING_CONFIG_LOCATION) vamos passar na hora do docker run
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
