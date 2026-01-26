# JAVA ARENA

Jeu de combat tactique au tour par tour en Java. Incarnez un dresseur, constituez une équipe de 3 monstres élémentaires et affrontez des créatures sauvages.

## Installation

**Prérequis** : Java 11+

```bash
git clone https://github.com/maeva87/JAVA-ARENA.git
cd JAVA-ARENA
javac projet/*.java
java projet.Main
```

## Fonctionnalités

- **Combat au tour par tour** avec système d'éléments (Feu > Plante > Eau > Feu)
- **Équipe de 3 monstres** générée aléatoirement à la création
- **Inventaire** : potions de soin, résurrection, outils de capture
- **Boutique** pour acheter des objets avec les crédits gagnés
- **Sauvegarde/Chargement** de la progression (format CSV)

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
├── Main.java           # Point d'entrée
├── Jeu.java            # Logique principale
├── Monstre.java        # Classe abstraite
├── MonstreFeu.java     # Type Feu
├── MonstreEau.java     # Type Eau
├── MonstrePlante.java  # Type Plante
├── Dresseur.java       # Joueur
├── Inventaire.java     # Gestion des objets
├── Combat.java         # Système de combat
├── Bestiaire.java      # Catalogue des monstres
├── Sauvegarde.java     # Persistance CSV
└── *Exception.java     # Exceptions personnalisées
```

## Licence

Projet académique - Ynov

