import java.util.Scanner;

public class Jeu {
    private Scanner scanner;
    private int credits;
    
    public Jeu() {
        this.scanner = new Scanner(System.in);
        this.credits = 10; // Crédit de base pour le joueur
    }
    
    public void demarrer() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Commencer une nouvelle partie ou charger une partie existante");
            System.out.println("2. Mon équipe (affiche l'état -> PV par monstres)");
            System.out.println("3. Mon joueur");
            System.out.println("4. Boutique");
            System.out.println("5. Quitter");
            
            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine();
            
            if (choix.equals("1")) {
                nouvelleOuChargerPartie();
            } else if (choix.equals("2")) {
                afficherEquipe();
            } else if (choix.equals("3")) {
                afficherJoueur();
            } else if (choix.equals("4")) {
                boutique();
            } else if (choix.equals("5")) {
                quitter();
                break;
            } else {
                System.out.println("Choix invalide !");
            }
        }
        
        scanner.close();
    }
    
    private void nouvelleOuChargerPartie() {
        System.out.println("Nouvelle/Charger partie (pas encore implémenté)");
    }
    
    private void afficherEquipe() {
        System.out.println("Mon équipe (pas encore implémenté)");
    }
    
    private void afficherJoueur() {
        System.out.println("Mon joueur (pas encore implémenté)");
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
        
        if (choix.equals("1")) {
            acheterItem(1, 3);
        } else if (choix.equals("2")) {
            acheterItem(2, 10);
        } else if (choix.equals("3")) {
            acheterItem(3, 1);
        } else if (choix.equals("4")) {
            break; // Retour au menu principal
        } else {
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
        System.out.println("Au revoir !");
    }
}