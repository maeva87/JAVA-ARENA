# 🎮 JAVA ARENA

## 📋 Présentation du Projet

**JAVA ARENA** est un jeu de combat tactique au tour par tour développé en Java. Incarnez un Dresseur et constituez une équipe de créatures élémentaires pour affronter des monstres sauvages dans des batailles captivantes.

Ce projet implémente l'ensemble du moteur de jeu avec une architecture logicielle robuste, la logique métier complète et un système de sauvegarde persistant.


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

### ⚙️ Actions Disponibles In-Game
- **Attaquer** : Lancez un combat contre un monstre sauvage
- **Utiliser un Objet** : Soignez, ressuscitez ou capturez un monstre
- **Consulter l'Équipe** : Visualisez l'état de vos monstres (PV)
- **Consulter le Joueur** : Consultez vos crédits et inventaire
- **Boutique** : Achetez des potions et outils
- **Sauvegarder** : Enregistrez votre progression

---

### Principes POO Appliqués

✅ **Héritage** : Hiérarchie Monstre → types spécialisés
✅ **Polymorphisme** : Calcul de dégâts adapté au type élémentaire
✅ **Encapsulation** : Getters/Setters pour tous les attributs
✅ **Classe Abstraite** : Monstre générique non instanciable
✅ **Collections Dynamiques** : ArrayList pour équipes, Map pour inventaire

---

