FROM eclipse-temurin:21

# Копируем скрипт ожидания
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Копируем JAR-файл (обратите внимание на правильное имя файла)
COPY build/libs/black-list-1.0.2.jar black-list.jar

# Команда запуска
CMD ["./wait-for-it.sh", "mysql-container:3306", "--", "java", "-Dspring.profiles.active=mysql-container", "-jar", "black-list.jar"]

EXPOSE 8080
