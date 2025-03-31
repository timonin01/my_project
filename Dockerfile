FROM eclipse-temurin:21
COPY my_pr/build/libs/insurance-calculator-1.0.2.jar insurance-calculator.jar
COPY my_pr/src/main/resources/db/changelog/ ./resources/db/changelog/
CMD ["java", "-jar", "insurance-calculator.jar"]
EXPOSE 8080
