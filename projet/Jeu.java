package projet;

import java.util.Scanner;

public class Jeu {
    private Scanner scanner;
    private int credits;
    private Dresseur joueur;
    private boolean enPartie;
    
    public Jeu() {
        this.credits = 10;
        this.joueur = null;
        this.enPartie = false;
    }
    
    public void demarrer() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.scanner = scanner;
            
            // Menu principal (avant de lancer une partie)
            while (!enPartie) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Commencer une nouvelle partie ou charger une partie existante");
                System.out.println("2. Quitter");
                
                System.out.print("\nVotre choix : ");
                String choix = scanner.nextLine();
                
                switch (choix) {
                    case "1":
                        nouvelleOuChargerPartie();
                        break;
                    case "2":
                        System.out.println("Au revoir !");
                        return;
                    default:
                        System.out.println("Choix invalide !");
                }
            }
            
            // Menu en jeu (une fois la partie lancée)
            menuEnJeu();
        }
    }
    
    private void menuEnJeu() {
        while (enPartie) {
            System.out.println("\n=== MENU EN JEU ===");
            System.out.println("1. Mon équipe (affiche l'état -> PV par monstres)");
            System.out.println("2. Mon joueur");
            System.out.println("3. Lancer un combat");
            System.out.println("4. Boutique");
            System.out.println("5. Quitter");
            
            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    afficherEquipe();
                    break;
                case "2":
                    afficherJoueur();
                    break;
                case "3":
                    lancerCombat();
                    break;
                case "4":
                    boutique();
                    break;
                case "5":
                    quitter();
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
    
    private void nouvelleOuChargerPartie() {
        System.out.println("\n=== NOUVELLE PARTIE / CHARGER ===");
        System.out.println("1 -> Commencer une nouvelle partie");
        System.out.println("2 -> Charger une partie existante");
        System.out.println("3 -> Retour");
        
        System.out.print("\nVotre choix : ");
        String choix = scanner.nextLine();
        
        switch (choix) {
            case "1":
                creerNouvellePartie();
                break;
            case "2":
                chargerPartie();
                break;
            case "3":
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }
    
    private void creerNouvellePartie() {
        System.out.print("Nom de votre équipe : ");
        String nom = scanner.nextLine();
        joueur = new Dresseur(nom);
        
        System.out.println("\nNouvelle partie créée !");
        System.out.println("Votre équipe :");
        joueur.afficherEquipe();
        
        enPartie = true;
    }
    
    private void afficherEquipe() {
        System.out.println("\n=== MON ÉQUIPE ===");
        for (int i = 0; i < joueur.getEquipe().size(); i++) {
            Monstre m = joueur.getEquipe().get(i);
            System.out.println((i + 1) + ". " + m.getNom() + " - " + m.getPvActuels() + "/" + m.getPvMax() + " PV - " + m.getElement().toString().toLowerCase());
        }
        System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
        scanner.nextLine();
    }
    
    private void afficherJoueur() {
        System.out.println("\n=== MON JOUEUR ===");
        System.out.println("Équipe : " + joueur.getNom());
        System.out.println("Crédits : " + credits);
        System.out.println("Monstres : " + joueur.getEquipe().size());
        System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
        scanner.nextLine();
    }
    
    private void boutique() {
        while (true) {
            System.out.println("\n=== BOUTIQUE ===");
            System.out.println("Crédits : " + credits);
            System.out.println("\n1 -> Potion de soin (+50 PV) - 5 crédits");
            System.out.println("2 -> Filet de capture - 8 crédits");
            System.out.println("3 -> Elixir de vie (résurrection) - 15 crédits");
            System.out.println("4 -> Retour");
            
            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    acheterItem("Potion", 5);
                    break;
                case "2":
                    acheterItem("Filet", 8);
                    break;
                case "3":
                    acheterItem("Elixir", 15);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void acheterItem(String nomItem, int prix) {
        if (joueur == null) {
            System.out.println("Créez d'abord une partie !");
            return;
        }
        if (credits >= prix) {
            credits -= prix;
            joueur.getInventaire().ajouterObjet(nomItem, 1);
            System.out.println(nomItem + " acheté avec succès !");
            System.out.println("Crédits restants : " + credits);
        } else {
            System.out.println("Pas assez de crédits ! Il vous manque " + (prix - credits) + " crédits.");
        }
    }
    
    private void lancerCombat() {
        if (!joueur.aDesMonstresDisponibles()) {
            System.out.println("Tous vos monstres sont KO ! Soignez-les d'abord.");
            return;
        }
        
        // Créer un monstre sauvage aléatoire
        java.util.Random random = new java.util.Random();
        String[] noms = {"Pyros", "Aqua", "Leafor", "Blazer", "Splash", "Racine"};
        Element[] elements = {Element.FEU, Element.EAU, Element.PLANTE};
        
        String nom = noms[random.nextInt(noms.length)];
        Element element = elements[random.nextInt(elements.length)];
        int pvMax = 50 + random.nextInt(100);
        int puissance = 10 + random.nextInt(20);
        
        Monstre monstreSauvage = new Monstre(nom, element, pvMax, puissance) {};
        
        // Créer un dresseur ennemi pour le combat
        Dresseur ennemi = new Dresseur("Sauvage");
        ennemi.ajouterMonstre(monstreSauvage);
        
        Combat combat = new Combat(joueur, ennemi);
        
        System.out.println("\n=== COMBAT ===");
        System.out.println("Un " + monstreSauvage.getNom() + " sauvage (" + monstreSauvage.getElement() + ") apparaît !");
        System.out.println("PV: " + monstreSauvage.getPvActuels() + "/" + monstreSauvage.getPvMax() + " - Attaque: " + monstreSauvage.getPuissanceAttaque());
        
        while (!combat.combatTermine()) {
            combat.afficherEtatCombat();
            
            System.out.println("1 -> Attaquer");
            System.out.println("2 -> Changer de monstre");
            System.out.println("3 -> Utiliser un objet");
            System.out.println("4 -> Fuir");
            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    try {
                        combat.attaquer(joueur, ennemi);
                        if (!combat.combatTermine()) {
                            combat.attaquer(ennemi, joueur);
                        }
                    } catch (MonstreKOException | CibleDejaKOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    joueur.afficherEquipe();
                    System.out.print("Numéro du monstre : ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        joueur.changerMonstre(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide !");
                    }
                    break;
                case "3":
                    utiliserObjetCombat(combat, ennemi);
                    break;
                case "4":
                    System.out.println("Vous avez fui le combat !");
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
        
        Dresseur gagnant = combat.getGagnant();
        if (gagnant == joueur) {
            int gain = 5 + random.nextInt(6);
            credits += gain;
            System.out.println("\nVous avez gagné ! +" + gain + " crédits !");
        } else {
            System.out.println("\nVous avez perdu...");
        }
        
        System.out.println("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    private void utiliserObjetCombat(Combat combat, Dresseur ennemi) {
        joueur.getInventaire().afficher();
        System.out.println("\n1 -> Potion (soigne 50 PV)");
        System.out.println("2 -> Elixir (ressuscite un monstre KO)");
        System.out.println("3 -> Filet (capture si PV < 30%)");
        System.out.println("4 -> Retour");
        
        System.out.print("\nVotre choix : ");
        String choix = scanner.nextLine();
        
        switch (choix) {
            case "1":
                if (!joueur.getInventaire().possede("Potion")) {
                    System.out.println("Vous n'avez pas de Potion !");
                    return;
                }
                joueur.afficherEquipe();
                System.out.print("Sur quel monstre ? (numéro) : ");
                try {
                    int index = Integer.parseInt(scanner.nextLine()) - 1;
                    if (index < 0 || index >= joueur.getEquipe().size()) {
                        System.out.println("Numéro invalide !");
                        return;
                    }
                    Monstre cible = joueur.getEquipe().get(index);
                    if (cible.estKO()) {
                        System.out.println("Ce monstre est KO ! Utilisez un Elixir.");
                        return;
                    }
                    if (cible.getPvActuels() == cible.getPvMax()) {
                        System.out.println("Ce monstre a déjà tous ses PV !");
                        return;
                    }
                    joueur.getInventaire().utiliserObjet("Potion");
                    cible.soigner(50);
                    System.out.println(cible.getNom() + " récupère 50 PV ! (" + cible.getPvActuels() + "/" + cible.getPvMax() + ")");
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide !");
                }
                break;
                
            case "2":
                if (!joueur.getInventaire().possede("Elixir")) {
                    System.out.println("Vous n'avez pas d'Elixir !");
                    return;
                }
                joueur.afficherEquipe();
                System.out.print("Quel monstre KO ressusciter ? (numéro) : ");
                try {
                    int index = Integer.parseInt(scanner.nextLine()) - 1;
                    if (index < 0 || index >= joueur.getEquipe().size()) {
                        System.out.println("Numéro invalide !");
                        return;
                    }
                    Monstre cible = joueur.getEquipe().get(index);
                    if (!cible.estKO()) {
                        System.out.println("Ce monstre n'est pas KO !");
                        return;
                    }
                    joueur.getInventaire().utiliserObjet("Elixir");
                    cible.soigner(cible.getPvMax() / 2);
                    System.out.println(cible.getNom() + " est ressuscité avec " + cible.getPvActuels() + " PV !");
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide !");
                }
                break;
                
            case "3":
                if (!joueur.getInventaire().possede("Filet")) {
                    System.out.println("Vous n'avez pas de Filet !");
                    return;
                }
                Monstre monstreSauvage = ennemi.getMonstreActif();
                double pourcentagePV = (double) monstreSauvage.getPvActuels() / monstreSauvage.getPvMax() * 100;
                if (pourcentagePV > 30) {
                    System.out.println("Le monstre a trop de PV pour être capturé ! (" + String.format("%.0f", pourcentagePV) + "% restants, il faut < 30%)");
                    return;
                }
                joueur.getInventaire().utiliserObjet("Filet");
                java.util.Random random = new java.util.Random();
                if (random.nextInt(100) < 70) {
                    System.out.println("Capture réussie ! " + monstreSauvage.getNom() + " rejoint votre équipe !");
                    joueur.ajouterMonstre(new Monstre(monstreSauvage.getNom(), monstreSauvage.getElement(), 
                                        monstreSauvage.getPvMax(), monstreSauvage.getPuissanceAttaque()) {});
                    monstreSauvage.recevoirDegats(monstreSauvage.getPvActuels()); // KO pour terminer combat
                } else {
                    System.out.println("Le monstre s'est échappé du filet !");
                }
                break;
                
            case "4":
                return;
                
            default:
                System.out.println("Choix invalide !");
        }
    }
    
    private void quitter() {
        System.out.println("\n=== QUITTER ===");
        System.out.println("1 -> Sauvegarder et quitter");
        System.out.println("2 -> Quitter sans sauvegarder");
        System.out.println("3 -> Retour");
        
        System.out.print("\nVotre choix : ");
        String choix = scanner.nextLine();
        
        switch (choix) {
            case "1":
                Sauvegarde.sauvegarder(joueur, credits);
                System.out.println("Au revoir !");
                enPartie = false;
                break;
            case "2":
                System.out.println("Au revoir !");
                enPartie = false;
                break;
            case "3":
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }
    
    private void chargerPartie() {
        if (!Sauvegarde.sauvegardeExiste()) {
            System.out.println("Aucune sauvegarde trouvée !");
            return;
        }
        
        DonneesSauvegarde donnees = Sauvegarde.charger();
        if (donnees == null) {
            return;
        }
        
        // Recréer le joueur avec les données chargées
        joueur = new Dresseur(donnees.nomDresseur);
        credits = donnees.credits;
        
        // Ajouter les monstres
        for (Monstre m : donnees.monstres) {
            joueur.ajouterMonstre(m);
        }
        
        // Restaurer l'inventaire
        joueur.getInventaire().getObjets().clear();
        for (java.util.Map.Entry<String, Integer> entry : donnees.inventaire.entrySet()) {
            joueur.getInventaire().ajouterObjet(entry.getKey(), entry.getValue());
        }
        
        System.out.println("Bienvenue " + joueur.getNom() + " !");
        System.out.println("Crédits : " + credits);
        joueur.afficherEquipe();
        
        enPartie = true;
    }
}