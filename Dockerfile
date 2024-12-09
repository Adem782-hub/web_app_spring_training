# Étape 1: Build l'application avec Maven
FROM maven:3.9.9 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Étape 2: Créer une image Java de production
FROM eclipse-temurin:21-jdk
COPY --from=build /home/app/target/maven_training-1.0.0-SNAPSHOT.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]

