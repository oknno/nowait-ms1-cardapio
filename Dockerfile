FROM amazoncorretto:17-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado pelo Maven para dentro do container
COPY target/nowait-ms1-cardapio-1.0.0-SNAPSHOT.jar app.jar

# Porta exposta pelo Spring Boot (padrão 8080)
EXPOSE 8080

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
