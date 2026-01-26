# JAVA ARENA

Jeu de combat tactique au tour par tour en Java. Incarnez un dresseur, constituez une équipe de 3 monstres élémentaires et affrontez des créatures sauvages.

## Sommaire

- [Installation](#installation)
- [Fonctionnalités Détaillées](#fonctionnalités-détaillées)
  - [Système de Combat](#système-de-combat)
  - [Gestion de l'Équipe](#gestion-de-léquipe)
  - [Inventaire & Boutique](#inventaire--boutique)
  - [Sauvegarde](#sauvegarde)
- [Particularités Techniques](#particularités-techniques)
  - [Architecture POO](#architecture-poo)
  - [Collections Utilisées](#collections-utilisées)
  - [Exceptions Personnalisées](#exceptions-personnalisées)
- [Commandes](#commandes)
- [Architecture](#architecture)
- [Screenshots](#screenshots)
- [Licence](#licence)

## Installation

**Prérequis** : Java 11+

```bash
git clone https://github.com/maeva87/JAVA-ARENA.git
cd JAVA-ARENA
javac projet/*.java
java projet.Main
```

## Fonctionnalités Détaillées

### Système de Combat

- **Combat au tour par tour** : affrontez des monstres sauvages générés aléatoirement
- **Mécanique Pierre-Feuille-Ciseaux** :
  - Feu > Plante (x2 dégâts)
  - Plante > Eau (x2 dégâts)
  - Eau > Feu (x2 dégâts)
- **Actions en combat** : Attaquer, Changer de monstre, Utiliser un objet, Fuir
- **Système de KO** : un monstre à 0 PV ne peut plus combattre

### Gestion de l'Équipe

- **3 monstres aléatoires** assignés à la création de la partie
- **9 espèces disponibles** dans le Bestiaire :
  - Feu : Pyroxis, Flammix, Salamandre
  - Eau : Aqualis, Hydrax, Tortank
  - Plante : Terragon, Verdax, Floramis
- **Caractéristiques** : Nom, PV actuels/max, Puissance d'attaque, Élément

### Inventaire & Boutique

| Objet | Effet | Prix |
|-------|-------|------|
| Potion | Restaure 50 PV | 5 crédits |
| Super Potion | Restaure 100 PV | 10 crédits |
| Elixir | Ressuscite un monstre KO (50% PV) | 15 crédits |
| Filet | Capture un monstre (< 30% PV) | 8 crédits |

- **Crédits** : gagnés en battant des monstres sauvages (5-10 par victoire)
- **Capture** : 70% de réussite si le monstre a moins de 30% de PV

### Sauvegarde

- **Format CSV** pour la persistance des données
- **Données sauvegardées** : nom d'équipe, crédits, monstres (type, PV, stats), inventaire
- **Nouvelle partie** : écrase l'ancienne sauvegarde

## Particularités Techniques

### Architecture POO

| Concept | Implémentation |
|---------|----------------|
| **Classe abstraite** | `Monstre` ne peut pas être instanciée directement |
| **Héritage** | `MonstreFeu`, `MonstreEau`, `MonstrePlante` héritent de `Monstre` |
| **Polymorphisme** | Méthode `calculerDegats()` redéfinie dans chaque sous-classe |
| **Encapsulation** | Tous les attributs sont privés avec getters/setters |

### Collections Utilisées

| Structure | Usage |
|-----------|-------|
| `ArrayList<Monstre>` | Équipe du joueur (taille variable) |
| `HashMap<String, Integer>` | Inventaire (recherche sans boucle) |

### Exceptions Personnalisées

| Exception | Déclencheur |
|-----------|-------------|
| `MonstreKOException` | Attaquer avec un monstre KO |
| `CibleDejaKOException` | Attaquer une cible déjà KO |
| `MonstreFullPVException` | Soigner un monstre avec tous ses PV |
| `CaptureImpossibleException` | Capturer un monstre avec > 30% PV |


## Commandes

| Action | Description |
|--------|-------------|
| Nouvelle partie | Crée une équipe avec 3 monstres aléatoires |
| Charger partie | Restaure la progression sauvegardée |
| Attaquer | Lance un combat contre un monstre sauvage |
| Inventaire | Utilise un objet sur un monstre |
| Boutique | Achète potions et outils |
| Sauvegarder | Enregistre la progression |

## Architecture

```
projet/
├── Main.java                    # Point d'entrée
├── Jeu.java                     # Boucle de jeu et menus
├── Monstre.java                 # Classe abstraite des monstres
├── MonstreFeu.java              # Sous-classe Feu
├── MonstreEau.java              # Sous-classe Eau
├── MonstrePlante.java           # Sous-classe Plante
├── Element.java                 # Enum des éléments
├── Dresseur.java                # Gestion du joueur
├── Inventaire.java              # HashMap des objets
├── Objet.java                   # Définition d'un objet
├── Boutique.java                # Catalogue et achats
├── Combat.java                  # Logique de combat
├── Bestiaire.java               # Création des monstres
├── Sauvegarde.java              # Lecture/écriture CSV
├── DonneesSauvegarde.java       # Modèle de données
├── MonstreKOException.java      # Exception personnalisée
├── CibleDejaKOException.java    # Exception personnalisée
├── MonstreFullPVException.java  # Exception personnalisée
└── CaptureImpossibleException.java # Exception personnalisée
```

## Screenshots

<img width="621" height="263" alt="image" src="https://github.com/user-attachments/assets/68f4effb-5396-46b5-8ab2-ca80f7cdf40c" />

<img width="436" height="346" alt="image" src="https://github.com/user-attachments/assets/c308c0e9-919e-43f5-846e-8c31a85bdfe4" />

<img width="326" height="104" alt="image" src="https://github.com/user-attachments/assets/797dff2c-4a94-42b4-a711-4720029a85cf" />

<img width="406" height="132" alt="image" src="https://github.com/user-attachments/assets/bfc34fe3-9100-4c12-a7cf-a04b5e035b16" />



## Licence

Projet académique - Ynov

