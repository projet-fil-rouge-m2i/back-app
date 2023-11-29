# Étape de build
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app

# Copie des fichiers de dépendance Maven (pom.xml)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie du code source et construction sans exécution des tests
COPY src ./src
RUN mvn clean package -DskipTests

# Étape de runtime
FROM openjdk:17
WORKDIR /app

# Copie de l'artefact build depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Point d'entrée pour l'exécution de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]