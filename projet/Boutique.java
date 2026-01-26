package projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Gère la boutique du jeu avec le catalogue d'objets
 */
public class Boutique {
    private List<Objet> catalogue;
    
    public Boutique() {
        this.catalogue = new ArrayList<>();
        initialiserCatalogue();
    }
    
    private void initialiserCatalogue() {
        catalogue.add(new Objet("Potion", "+50 PV", 15, Objet.TypeObjet.POTION));
        catalogue.add(new Objet("Super Potion", "+100 PV", 30, Objet.TypeObjet.POTION));
        catalogue.add(new Objet("Elixir", "Ressuscite avec 50% PV", 50, Objet.TypeObjet.ELIXIR));
        catalogue.add(new Objet("Filet", "Capture (monstre < 30% PV)", 16, Objet.TypeObjet.CAPTURE));
    }
    
    /**
     * Affiche et gère le menu de la boutique
     */
    public int ouvrir(Scanner scanner, int credits, Inventaire inventaire) {
        while (true) {
            System.out.println("\n=== BOUTIQUE ===");
            System.out.println("Crédits disponibles : " + credits);
            // Afficher l'inventaire du joueur
            System.out.println("Inventaire :");
            boolean inventaireVide = true;
            for (String nom : new String[]{"Potion", "Super Potion", "Elixir", "Filet"}) {
                int qte = inventaire.getQuantite(nom);
                if (qte > 0) {
                    System.out.println("- " + nom + " x" + qte);
                    inventaireVide = false;
                }
            }
            if (inventaireVide) {
                System.out.println("Aucun objet");
            }
            System.out.println();

            // Afficher le catalogue
            for (int i = 0; i < catalogue.size(); i++) {
                System.out.println((i + 1) + ". " + catalogue.get(i));
            }
            System.out.println((catalogue.size() + 1) + ". Retour");

            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine();

            try {
                int index = Integer.parseInt(choix) - 1;

                if (index == catalogue.size()) {
                    return credits; // Retour
                }

                if (index >= 0 && index < catalogue.size()) {
                    credits = acheter(catalogue.get(index), credits, inventaire);
                } else {
                    System.out.println("Choix invalide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre !");
            }
        }
    }
    
    private int acheter(Objet objet, int credits, Inventaire inventaire) {
        if (credits >= objet.getPrix()) {
            credits -= objet.getPrix();
            inventaire.ajouterObjet(objet.getNom(), 1);
            System.out.println(objet.getNom() + " acheté !");
            System.out.println("Crédits restants : " + credits);
        } else {
            System.out.println("Pas assez de crédits ! (manque " + (objet.getPrix() - credits) + ")");
        }
        return credits;
    }
    
    public List<Objet> getCatalogue() {
        return catalogue;
    }
}
