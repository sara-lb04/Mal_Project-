# Système de Gestion de Bibliothèque Universitaire

## Description du Projet :

Ce projet est un système simplifié de gestion pour la bibliothèque de l'Université de Bouira,
L'objectif est de concevoir et d'implémenter un système permettant de gérer les livres, les utilisateurs (Administrateurs et Lecteurs) et les emprunts, en appliquant plusieurs design patterns.

## Fonctionnalités

* **Gestion des Livres :**
    * Ajouter de nouveaux livres .
    * Supprimer des livres .
    * Rechercher des livres par titre.
    * Afficher la liste des livres disponibles.
* **Gestion des Utilisateurs :**
    * Inscription de nouveaux utilisateurs (Admin ou Lecteur) .
    * Connexion d'utilisateurs existants.
    * emprunt de livres.
* **Gestion des Emprunts :**
    * Emprunter des livres .
    * Afficher la liste des emprunts .
* **Notifications :**
    * Notification automatique des Lecteurs abonnés lors de l'ajout d'un nouveau livre .
* **Interface Utilisateur :**
    * Interface graphique simple (GUI) développée avec Java Swing.

## Design Patterns Utilisés

Ce projet met en œuvre les design patterns suivants :

* **Singleton :** Assure une instance unique de la classe `Bibliotheque` (`Bibliotheque.getInstance()`).
* **Factory Method :** Utilisé pour la création des différents types d'utilisateurs (`Admin`, `Lecteur`) via la hiérarchie `UtilisateurCreator`, `AdminCreator`, `LecteurCreator`.
* **Proxy :** Contrôle l'accès aux opérations sensibles de la `Bibliotheque` (ajout et suppression de livres), en restreignant l'accès aux utilisateurs de type `Admin` (`BibliothequeProxy`).
* **Observer :**Ce pattern est appliqué pour mettre en place un système de notification. La classe NotificationSystem agit comme le "Sujet", et les classes qui implémentent l'interface `Observateur` (notamment la classe Lecteur) agissent comme des "Observateurs". Lorsqu'un nouvel événement pertinent se produit (comme l'ajout d'un nouveau livre), le Sujet notifie automatiquement tous ses Observateurs abonnés, qui peuvent alors réagir en conséquence (par exemple, afficher un message pour le lecteur).

## Architecture et Structure du Code

Le projet est structuré en plusieurs classes représentant les différentes entités et logiques du système :

* `Livre` : Représente un livre.
* `Utilisateur`, `Admin`, `Lecteur` : Hiérarchie pour gérer les différents types d'utilisateurs.
* `Emprunt` : Représente un enregistrement d'emprunt.
* `Bibliotheque` : Le cœur du système, gère les listes de livres, utilisateurs, emprunts, et le système de notification (Singleton).
* `UtilisateurCreator`, `AdminCreator`, `LecteurCreator` : Classes responsables de la création des utilisateurs (Factory Method).
* `Observateur`, `NotificationSystem` : Composants du pattern Observer pour les notifications.
* `BibliothequeProxy` : Fournit une couche de contrôle d'accès pour les opérations de la `Bibliotheque` (Proxy).
* `BibliothequeGUI` : L'interface graphique utilisateur (Swing).

Les données sont stockées en mémoire en utilisant des `ArrayList`.


## Modélisation

La structure du système est modélisée à l'aide d'un diagramme de classes UML. Ce diagramme représente les différentes classes, leurs attributs, leurs méthodes et les relations qui les unissent (héritage, implémentation, association, dépendance).

Les principales classes modélisées sont :
* **Bibliotheque:** Représente l'instance unique de la bibliothèque gérant l'ensemble du système.
* **Livre:** Représente les ouvrages disponibles.
* **Utilisateur**,**Admin**,**Lecteur :** Modélisent la hiérarchie des utilisateurs du système.
* **Emprunt:** Enregistre l'association entre un lecteur et un livre emprunté.
* **UtilisateurCreator**,**AdminCreator**,**AdminCreator** : Gèrent la création des différents types d'utilisateurs via le pattern Factory Method.
* **Observateur**,**NotificationSystem** :Composants du pattern Observer pour la gestion des notifications.
* **BibliothequeProxy :** Contrôle l'accès à certaines fonctionnalités de la bibliothèque via le pattern Proxy.
* **BibliothequeGUI :** Fournit l'interface graphique à l'utilisateur.

## Auteurs

* Laoubi Sara Sabrine (Spécialité/Groupe : ISIL - Groupe 02)

## Contexte du Projet

* **Université :** Université de Bouira
* **Faculté :** Sciences Exactes
* **Département :** Informatique
* **Cours :** Modélisation et Architectures Logicielles
* **Année Universitaire :** 2024/2025
* **Enseignant :** Mr. Haithem Benhalima

