# Back_app, back-end du TP fil rouge

back-end en spring boot contenant toute la logique métier de l'application

## 1. Lancer le projet sans docker

### 1.1. cloner le projet

```
$ git clone git@github.com:projet-fil-rouge-m2i/back-app.git
```

### 1.2 Dépendances nécessaires

- Java 17
- Postgresql >= 14

### 1.3 Configuration

En local, dans le fichier `src/resources/application.properties`, vérifier url: `spring.datasource.url=jdbc:postgresql://db:5432/tp`
remplacer db par `localhost` ou `127.0.0.1` et le port 5432 par le port de votre choix. Par défaut, le port utilisé par postgre est `5432`

Vérifier également username: `spring.datasource.username=postgres` et `spring.datasource.password=root` qui doivent correspondre à votre configuration Postgresql


Le port par défaut du projet est `8080`

### 1.4 Lancer le projet

Vous pouvez lancer le projet en exécutant la commande à la racine du projet

```
$ ./mvnw spring-boot:run
```

Vous pouvez également exécuter le projet via intellij.

### 1.5 Documentation

Lorsque le projet est éxecuté sur le port par défaut, la documentation est accessible sur l'url : http://localhost:8080/swagger-ui/index.html

## 2 Lancer le projet via Docker (conseillé)

### 2.1 Configuration

Laisser le fichier src/resources/application.yml par défaut.

```
spring.datasource.url=jdbc:postgresql://db:5432/tp
spring.datasource.username=postgres
spring.datasource.password=root

spring.flyway.baseline-on-migrate=true
spring.flyway.out-of-order=true

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 2.2 Créer un container docker

Pour créer un container Docker du back_app, executer la commande :

```
$ docker-compose up --build
```

Le container contiendra les images suivantes :
- back-app
- postgresql
- pgadmin

### 2.3 Documentation

Lorsque le container est bien créé, la documentation est accessible sur l'url : http://localhost:8080/swagger-ui/index.html

### 2.4 Utilisation de pgadmin

Vous pouvez accéder à l'administration de la bdd sur : http://localhost:5050/
Sur la page de connection, utilisez les identifiant suivant
- Mail : `admin@admin.com`
- Password : `root`

Une fois authentifié, pour se connecter à la base de donnée, navigué vers Object -> Register -> Server.

Dans l'onglet General :
- Ajouter `tp` à name

Dans l'onglet Connection :
- Ajouter `db` à Host name/address
- Port `5432`
- Maintenance database `postgres`
- Username `postgres`
- Password : `root`

Puis Save, votre base de donnée devrait être accessible en déroulant la liste Servers situé à gauche.