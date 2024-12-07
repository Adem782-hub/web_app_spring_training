# Étape 1 : Utiliser Maven pour construire le projet
FROM maven:3.9.9 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Étape 2 : Utiliser une image JDK pour l'exécution
FROM eclipse-temurin:21-jdk
COPY --from=build /home/app/target/maven_training-1.0.0-SNAPSHOT.jar /usr/local/lib/app.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]

