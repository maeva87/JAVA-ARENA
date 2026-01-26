package projet;

import java.util.Scanner;

public class Jeu {
    private Scanner scanner;
    private int credits;
    private Dresseur joueur;
    
    public Jeu() {
        this.credits = 10;
        this.joueur = null;
    }
    
    public void demarrer() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.scanner = scanner;
            while (true) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Commencer une nouvelle partie ou charger une partie existante");
                System.out.println("2. Mon équipe (affiche l'état -> PV par monstres)");
                System.out.println("3. Mon joueur");
                System.out.println("4. Boutique");
                System.out.println("5. Quitter");
                
                System.out.print("\nVotre choix : ");
                String choix = scanner.nextLine();
                
                switch (choix) {
                    case "1":
                        nouvelleOuChargerPartie();
                        break;
                    case "2":
                        afficherEquipe();
                        break;
                    case "3":
                        afficherJoueur();
                        break;
                    case "4":
                        boutique();
                        break;
                    case "5":
                        quitter();
                        return;
                    default:
                        System.out.println("Choix invalide !");
                }
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
                System.out.println("Chargement (pas encore implémenté)");
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
        
        // Ajouter 3 monstres de base
        joueur.ajouterMonstre(new Monstre("Flamby", Element.FEU, 100, 25) {});
        joueur.ajouterMonstre(new Monstre("Aquali", Element.EAU, 150, 18) {});
        joueur.ajouterMonstre(new Monstre("Leafy", Element.PLANTE, 100, 20) {});
        
        System.out.println("\nNouvelle partie créée !");
        System.out.println("Votre équipe :");
        joueur.afficherEquipe();
    }
    
    private void afficherEquipe() {
        if (joueur == null) {
            System.out.println("Aucune partie en cours ! Créez d'abord une partie.");
            return;
        }
        
        System.out.println("\n=== MON ÉQUIPE ===");
        for (int i = 0; i < joueur.getEquipe().size(); i++) {
            Monstre m = joueur.getEquipe().get(i);
            System.out.println("Monstre " + (i + 1) + " - " + m.getPvActuels() + " PV - " + m.getElement().toString().toLowerCase());
        }
        System.out.println((joueur.getEquipe().size() + 1) + " -> Retour");
    }
    
    private void afficherJoueur() {
        if (joueur == null) {
            System.out.println("Aucune partie en cours !");
            return;
        }
        
        System.out.println("\n=== MON JOUEUR ===");
        System.out.println("Équipe : " + joueur.getNom());
        System.out.println("Crédits : " + credits);
        System.out.println("Monstres : " + joueur.getEquipe().size());
    }
    
    private void boutique() {
        while (true) {
            System.out.println("\n=== BOUTIQUE ===");
            System.out.println("Crédits : " + credits);
            System.out.println("\n1 -> Item 1 - 3 crédits");
            System.out.println("2 -> Item 2 - 10 crédits");
            System.out.println("3 -> Item 3 - 1 crédits");
            System.out.println("4 -> Retour");
            
            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    acheterItem(1, 3);
                    break;
                case "2":
                    acheterItem(2, 10);
                    break;
                case "3":
                    acheterItem(3, 1);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void acheterItem(int numeroItem, int prix) {
        if (credits >= prix) {
            credits -= prix;
            System.out.println("Item " + numeroItem + " acheté avec succès !");
            System.out.println("Crédits restants : " + credits);
        } else {
            System.out.println("Pas assez de crédits ! Il vous manque " + (prix - credits) + " crédits.");
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
                System.out.println("Sauvegarde (pas encore implémenté)");
                System.out.println("Au revoir !");
                break;
            case "2":
                System.out.println("Au revoir !");
                break;
            case "3":
                demarrer();
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }
}