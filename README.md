# 🎮 JAVA ARENA

## 📋 Présentation du Projet

**JAVA ARENA** est un jeu de combat tactique au tour par tour développé en Java. Incarnez un Dresseur et constituez une équipe de créatures élémentaires pour affronter des monstres sauvages dans des batailles captivantes.

Ce projet implémente l'ensemble du moteur de jeu avec une architecture logicielle robuste, la logique métier complète et un système de sauvegarde persistant.

---

## 🎯 Fonctionnalités Principales

### ⚔️ Système de Combat
- Combats au tour par tour contre des monstres aléatoires
- Mécanique **Pierre-Feuille-Ciseaux** élémentaire :
  - **Eau** → efficace contre le Feu (x2 dégâts)
  - **Feu** → efficace contre la Plante (x2 dégâts)
  - **Plante** → efficace contre l'Eau (x2 dégâts)
- Système de KO : un monstre avec 0 PV ne peut ni attaquer ni être attaqué
- Gain de crédits à la victoire

### 👤 Gestion du Dresseur
- Création d'une équipe personnalisée avec 3 monstres (sélection aléatoire)
- Inventaire dynamique contenant :
  - Potions de soin
  - Potions de résurrection
  - Outils de capture
- Gestion de crédits pour acheter des objets

### 🏪 Boutique
- Achat de potions et outils de capture
- Système d'économie basé sur les crédits gagnés

### 💾 Sauvegarde/Chargement
- Persistance complète de la progression (équipe, PV, inventaire)
- Format CSV pour la sauvegarde des données
- Restauration intégrale du jeu à redémarrage

### 🎪 Bestiaire
Trois types de monstres élémentaires :
- **Monstres de Feu** 🔥
- **Monstres d'Eau** 💧
- **Monstres de Plante** 🌿

---

## 🚀 Installation & Lancement

### Prérequis
- **Java 11** ou supérieur
- Un terminal/invite de commande

### Étapes de Lancement

#### 1. **Cloner le repository**
```bash
git clone https://github.com/maeva87/JAVA-ARENA.git
cd JAVA-ARENA/projet
```

#### 2. **Compiler le code**
```bash
javac *.java
```

#### 3. **Lancer le jeu**
```bash
java Main
```

Le jeu démarre directement dans la console avec le menu principal.

---

## 📖 Guide de Jeu

### 🎮 Menu Principal
```
1. Nouvelle Partie      → Crée une équipe avec 3 monstres aléatoires
2. Charger Partie       → Restaure votre progression précédente
3. Quitter              → Ferme le jeu avec sauvegarde optionnelle
```

### ⚙️ Actions Disponibles In-Game
- **Attaquer** : Lancez un combat contre un monstre sauvage
- **Utiliser un Objet** : Soignez, ressuscitez ou capturez un monstre
- **Consulter l'Équipe** : Visualisez l'état de vos monstres (PV)
- **Consulter le Joueur** : Consultez vos crédits et inventaire
- **Boutique** : Achetez des potions et outils
- **Sauvegarder** : Enregistrez votre progression

---

## 🏗️ Architecture du Projet

### Structure des Classes

```
Monstre (classe abstraite)
    ├── MonstreFeu
    ├── MonstreEau
    └── MonstrePlante

Dresseur
    ├── équipe (List<Monstre>)
    ├── inventaire (Inventaire)
    └── crédits (int)

Inventaire
    └── gestion d'objets avec quantités (Map)

Bestiaire
    └── catalogue de tous les monstres disponibles

Combat
    └── logique et déroulement des combats

Sauvegarde
    └── persistance des données (CSV)

Jeu
    └── orchestration principale et boucle de jeu
```

### Principes POO Appliqués

✅ **Héritage** : Hiérarchie Monstre → types spécialisés
✅ **Polymorphisme** : Calcul de dégâts adapté au type élémentaire
✅ **Encapsulation** : Getters/Setters pour tous les attributs
✅ **Classe Abstraite** : Monstre générique non instanciable
✅ **Collections Dynamiques** : ArrayList pour équipes, Map pour inventaire

---

## ⚠️ Système d'Exceptions Personnalisées

Le jeu utilise des exceptions pour garantir la robustesse :

| Exception | Situation |
|-----------|-----------|
| `MonstreKOException` | Tentative d'attaque avec un monstre KO |
| `CibleDejaKOException` | Tentative d'attaque sur une cible KO |
| Validation d'entrée | Saisies utilisateur invalides |

---

## 📁 Structure des Fichiers

```
projet/
    ├── Main.java                    # Point d'entrée
    ├── Jeu.java                     # Orchestration du jeu
    ├── Monstre.java                 # Classe abstraite
    ├── MonstreFeu.java              # Type Feu
    ├── MonstreEau.java              # Type Eau
    ├── MonstrePlante.java           # Type Plante
    ├── Dresseur.java                # Gestion du joueur
    ├── Inventaire.java              # Gestion des objets
    ├── Bestiaire.java               # Catalogue des monstres
    ├── Combat.java                  # Logique de combat
    ├── Element.java                 # Énumération des éléments
    ├── Sauvegarde.java              # Persistance (CSV)
    ├── DonneesSauvegarde.java       # Modèle de sauvegarde
    ├── MonstreKOException.java       # Exception personnalisée
    └── CibleDejaKOException.java     # Exception personnalisée

sauvegarde.csv                       # Fichier de persistance (généré)
```

---

## 💾 Format de Sauvegarde

Les données sont sauvegardées au format **CSV** :

```csv
# JOUEUR
nom_équipe,crédits

# MONSTRES
nom,type,pv_max,pv_actuels,puissance

# INVENTAIRE
nom_objet,quantité
```

---

## 🎮 Exemple de Partie

```
=== JAVA ARENA ===
1. Nouvelle Partie
2. Charger Partie
3. Quitter

Choix > 1

Bienvenue, Dresseur ! Quel nom pour votre équipe ?
> Team Dragon

Vos 3 monstres ont été assignés aléatoirement :
- Salamandre (Feu) - 45 PV
- Tortank (Eau) - 50 PV
- Florizarre (Plante) - 48 PV

[Menu Principal]
1. Attaquer
2. Consulter équipe
3. Consulter inventaire
4. Boutique
5. Sauvegarder
6. Quitter

Choix > 1

Vous êtes attaqué par Squirtle (Eau) - 35 PV !

Combat :
- Votre Salamandre (Feu) attaque Squirtle !
  Dégâts : 15 (pas d'avantage)
- Squirtle contre-attaque !
  Dégâts : 10 (avantage Eau) = 20 dégâts !
...
```

---

## ✨ Points Forts du Projet

- ✅ **POO Rigoureuse** : Architecture clean et maintenable
- ✅ **Robustesse** : Gestion complète des erreurs et des cas limites
- ✅ **Persistance** : Sauvegarde/chargement intégrés
- ✅ **Gameplay** : Système de combat équilibré et stratégique
- ✅ **UX Console** : Interface claire et intuitive

---

## 👨‍💻 Développé par
**Studio de Jeux Indépendants** - Projet JAVA ARENA

---

## 📝 Licence
Projet académique - Université Ynov

---

**Prêt à relever le défi ? Lancez le jeu et devenez le Dresseur ultime !** 🏆