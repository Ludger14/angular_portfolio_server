From maven:2.5.4-openjdk-11 AS build
COPY . .
RUN mvn clean package br.com.portfolio

FROM openjdk:11.0.1-jdk-slim
COPY --from=build /target/portfolio-0.0.1-SNAPSHOT.jar portfolio.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "portfolio.jar" ]
