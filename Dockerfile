FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean test

CMD ["mvn", "test"]

CMD ["mvn", "allure:report"]