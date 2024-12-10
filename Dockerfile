# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.9 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Étape 2 : Créer l'image finale avec JDK
FROM eclipse-temurin:21-jdk
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/maven_training-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

