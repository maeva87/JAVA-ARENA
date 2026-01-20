package projet;

import java.util.Scanner;

public class Jeu {
    private Scanner scanner;
    
    public Jeu() {
        this.scanner = new Scanner(System.in);
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
                System.out.println("❌ Choix invalide !");
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
        System.out.println("Boutique (pas encore implémenté)");
    }
    
    private void quitter() {
        System.out.println("Au revoir !");
    }
}