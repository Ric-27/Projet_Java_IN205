# Application de gestion d’une bibliothèque

Ce projet consiste à créer une application web pour gérer les membres, les emprunts et les livres d'une bibliothèque.

##Version de java

Pour Exécuter les version de java et maven utilisées:

```bash
Apache Maven 3.6.0
java 13.0.2 2020-01-14
javac 13.0.2
```

Mais le program fonctionne avec java 8 ou plus.

##Usage
Pour exécuter il faut être dans le dossier LibraryManager et exécuter le command:

```bash
mvn exec:java
```

Pour remplir la base de données.
Après il faut compiler avec:

```bash
mvn clean install
```

Et finalement monter le dossier sur le serveur Tomcat et ouvrir-le dans votre navigateur internet.
