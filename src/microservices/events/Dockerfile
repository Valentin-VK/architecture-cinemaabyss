# Первая стадия: Сборка проекта
FROM maven:3.8-openjdk-11 AS builder

WORKDIR /usr/src/app

# Клонируем репозиторий или копируем код
COPY pom.xml .
#RUN mvn dependency:go-offline

# Полностью скопируем весь проект
COPY src src

RUN mvn clean package -DskipTests

# Вторая стадия: Создание минимального рабочего образа
FROM openjdk:11-jre

# Создаем рабочую директорию
WORKDIR /opt/app

# Переносим собранный jar-файл из первой стадии
COPY --from=builder /usr/src/app/target/*.jar app.jar

# Устанавливаем точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]

# Открытый порт приложения
EXPOSE 8082