# Выбор базы Docker-образа
FROM openjdk:11-jre-slim

# Копируем артефакт сборки Spring Boot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Устанавливаем точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Открытый порт приложения
EXPOSE 8082