Веб-приложение для страхования, где пользователь вводит свои данные и риски от которых он хочет застраховаться, программа рассчитывает коэффициенты и выводит итоговую стоимость страховки.
В проекте 3 микросервиса:
- insurance-calculator-app:
Осуществляет основную логику работы приложения - рассчитывая страховку
- doc-generator-app:
Данные передаются из insurance-calculator-app в doc-generator-app через Rabbit MQ, затем doc-generator-app создает pdf файл на основе введенных данных пользователем.
- black-list-app
Осуществляет проверку, находится ли пользователь в черном списке, если да, то программа выдает ошибку.



Технологии применяемые в проекте:
* VCS, Git, GitHub
* Build Tool Gradle
* Spring Framework, Spring Boot
* Jackson
* SLF4J, Logback  
* Design patterns: IoC, Builder, DTO, Factory, Strategy, etc.
* REST, WEB MVC
* SQL, MySQL, H2, JDBC, ORM, JPA, Hibernate, Liquibase
* JUnit, Mockito
* Lombok, Google Guava, Jakarta Expression Language (EL)
* JAR, WAR, Java Web Server, Apache Tomcat
* JSON, XML
* Containers, Docker
* Message Broker, RabbitMQ
* Thymeleaf 
* SpringDoc OpenAPI
