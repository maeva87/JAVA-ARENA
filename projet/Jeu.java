package projet;

import java.util.Scanner;

public class Jeu {
    private Scanner scanner;
    private int credits;
    private Dresseur joueur;
    private boolean enPartie;
    private Boutique boutique;
    
    public Jeu() {
        this.credits = 10;
        this.joueur = null;
        this.enPartie = false;
        this.boutique = new Boutique();
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
            System.out.println("1. Mon équipe");
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
                    ouvrirBoutique();
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
            String statut = m.estKO() ? " [KO]" : "";
            System.out.println((i + 1) + ". " + m.getNom() + " - " + m.getPvActuels() + "/" + m.getPvMax() + " PV - " + m.getElement().toString().toLowerCase() + statut);
        }
        
        joueur.getInventaire().afficher();
        
        System.out.println("\n1 -> Utiliser une Potion (soigne 50 PV)");
        System.out.println("2 -> Utiliser un Elixir (ressuscite un monstre KO)");
        System.out.println("3 -> Retour");
        
        System.out.print("\nVotre choix : ");
        String choix = scanner.nextLine();
        
        switch (choix) {
            case "1":
                utiliserPotionEquipe();
                break;
            case "2":
                utiliserElixirEquipe();
                break;
            case "3":
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }
    
    private void utiliserPotionEquipe() {
        if (!joueur.getInventaire().possede("Potion")) {
            System.out.println("Vous n'avez pas de Potion !");
            return;
        }
        
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
            joueur.getInventaire().utiliserObjet("Potion");
            cible.soigner(50);
            System.out.println(cible.getNom() + " récupère des PV ! (" + cible.getPvActuels() + "/" + cible.getPvMax() + ")");
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide !");
        } catch (MonstreFullPVException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void utiliserElixirEquipe() {
        if (!joueur.getInventaire().possede("Elixir")) {
            System.out.println("Vous n'avez pas d'Elixir !");
            return;
        }
        
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
            cible.ressusciter(cible.getPvMax() / 2);
            System.out.println(cible.getNom() + " est ressuscité avec " + cible.getPvActuels() + " PV !");
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide !");
        }
    }
    
    private void afficherJoueur() {
        System.out.println("\n=== MON JOUEUR ===");
        System.out.println("Équipe : " + joueur.getNom());
        System.out.println("Crédits : " + credits);
        System.out.println("Monstres : " + joueur.getEquipe().size());
        System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
        scanner.nextLine();
    }
    
    private void ouvrirBoutique() {
        if (joueur == null) {
            System.out.println("Créez d'abord une partie !");
            return;
        }
        credits = boutique.ouvrir(scanner, credits, joueur.getInventaire());
    }
    
    private void lancerCombat() {
        if (!joueur.aDesMonstresDisponibles()) {
            System.out.println("Tous vos monstres sont KO ! Soignez-les d'abord.");
            return;
        }
        
        // Créer un monstre sauvage aléatoire depuis le Bestiaire
        Monstre monstreSauvage = Bestiaire.creerMonstreAleatoire();
        
        // Créer un dresseur ennemi pour le combat
        Dresseur ennemi = new Dresseur("Sauvage");
        ennemi.ajouterMonstre(monstreSauvage);
        
        Combat combat = new Combat(joueur, ennemi);
        
        System.out.println("\n=== COMBAT ===");
        System.out.println("Un " + monstreSauvage.getNom() + " sauvage (" + monstreSauvage.getElement() + ") apparaît !");
        System.out.println("PV: " + monstreSauvage.getPvActuels() + "/" + monstreSauvage.getPvMax() + " - Attaque: " + monstreSauvage.getPuissanceAttaque());
        
        java.util.Random random = new java.util.Random();
        
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
                    joueur.getInventaire().utiliserObjet("Potion");
                    cible.soigner(50);
                    System.out.println(cible.getNom() + " récupère 50 PV ! (" + cible.getPvActuels() + "/" + cible.getPvMax() + ")");
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide !");
                } catch (MonstreFullPVException e) {
                    System.out.println(e.getMessage());
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
                    cible.ressusciter(cible.getPvMax() / 2);
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
                try {
                    verifierCapturePossible(monstreSauvage);
                    joueur.getInventaire().utiliserObjet("Filet");
                    java.util.Random random = new java.util.Random();
                    if (random.nextInt(100) < 70) {
                        System.out.println("Capture réussie !");
                        // Créer le monstre capturé avec la bonne sous-classe
                        Monstre monstreCapture;
                        switch (monstreSauvage.getElement()) {
                            case FEU:
                                monstreCapture = new MonstreFeu(monstreSauvage.getNom(), monstreSauvage.getPvMax(), monstreSauvage.getPuissanceAttaque());
                                break;
                            case EAU:
                                monstreCapture = new MonstreEau(monstreSauvage.getNom(), monstreSauvage.getPvMax(), monstreSauvage.getPuissanceAttaque());
                                break;
                            default:
                                monstreCapture = new MonstrePlante(monstreSauvage.getNom(), monstreSauvage.getPvMax(), monstreSauvage.getPuissanceAttaque());
                        }
                        // Conserver les PV actuels du monstre capturé
                        int degatsSubis = monstreSauvage.getPvMax() - monstreSauvage.getPvActuels();
                        if (degatsSubis > 0) {
                            monstreCapture.recevoirDegats(degatsSubis);
                        }
                        
                        // Vérifier si l'équipe est complète
                        if (joueur.getEquipe().size() >= 6) {
                            System.out.println("Votre équipe est complète ! Voulez-vous remplacer un monstre ?");
                            joueur.afficherEquipe();
                            System.out.println("0 -> Relâcher " + monstreCapture.getNom());
                            System.out.print("Quel monstre remplacer ? (numéro ou 0) : ");
                            try {
                                int indexRemplace = Integer.parseInt(scanner.nextLine());
                                if (indexRemplace == 0) {
                                    System.out.println(monstreCapture.getNom() + " a été relâché.");
                                } else if (indexRemplace >= 1 && indexRemplace <= joueur.getEquipe().size()) {
                                    Monstre ancienMonstre = joueur.getEquipe().get(indexRemplace - 1);
                                    joueur.getEquipe().set(indexRemplace - 1, monstreCapture);
                                    System.out.println(ancienMonstre.getNom() + " a été relâché. " + monstreCapture.getNom() + " rejoint votre équipe !");
                                } else {
                                    System.out.println("Choix invalide. " + monstreCapture.getNom() + " a été relâché.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrée invalide. " + monstreCapture.getNom() + " a été relâché.");
                            }
                        } else {
                            joueur.ajouterMonstre(monstreCapture);
                            System.out.println(monstreCapture.getNom() + " rejoint votre équipe !");
                        }
                        
                        monstreSauvage.recevoirDegats(monstreSauvage.getPvActuels()); // KO pour terminer combat
                    } else {
                        System.out.println("Le monstre s'est échappé du filet !");
                    }
                } catch (CaptureImpossibleException e) {
                    System.out.println(e.getMessage());
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
        
        // Recréer le joueur avec les données chargées (sans monstres aléatoires)
        joueur = new Dresseur(donnees.nomDresseur, true);
        credits = donnees.credits;
        
        // Ajouter les monstres sauvegardés
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
    
    // Méthode pour vérifier si la capture est possible
    private void verifierCapturePossible(Monstre monstre) throws CaptureImpossibleException {
        double pourcentagePV = (double) monstre.getPvActuels() / monstre.getPvMax() * 100;
        if (pourcentagePV > 30) {
            throw new CaptureImpossibleException(
                "Le monstre a trop de PV pour être capturé ! (" + 
                String.format("%.0f", pourcentagePV) + "% restants, il faut < 30%)"
            );
        }
    }
}