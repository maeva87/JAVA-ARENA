package projet;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    // HashMap pour associer nom d'objet à quantité (sans boucle pour recherche)
    private Map<String, Integer> objets;
    
    public Inventaire() {
        this.objets = new HashMap<>();
        // Inventaire de départ
        objets.put("Potion", 3);          // Soigne 50 PV
        objets.put("Resurrection", 1);     // Ressuscite un monstre KO avec 50% PV
        objets.put("Pokeball", 2);         // Capture un monstre (si PV < 30%)
    }
    
    // Ajouter un objet (utilisé par la boutique)
    public void ajouterObjet(String nom, int quantite) {
        objets.put(nom, objets.getOrDefault(nom, 0) + quantite);
    }
    
    // Utiliser un objet (retourne true si succès)
    public boolean utiliserObjet(String nom) {
        Integer quantite = objets.get(nom);  // Pas de boucle grâce à HashMap
        if (quantite == null || quantite <= 0) {
            return false;
        }
        objets.put(nom, quantite - 1);
        return true;
    }
    
    // Obtenir la quantité d'un objet
    public int getQuantite(String nom) {
        return objets.getOrDefault(nom, 0);  // Pas de boucle
    }
    
    // Vérifier si un objet existe
    public boolean possede(String nom) {
        return objets.getOrDefault(nom, 0) > 0;
    }
    
    // Afficher l'inventaire
    public void afficher() {
        System.out.println("\n=== INVENTAIRE ===");
        if (objets.isEmpty() || objets.values().stream().allMatch(q -> q == 0)) {
            System.out.println("Inventaire vide !");
        } else {
            for (Map.Entry<String, Integer> entry : objets.entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.println("- " + entry.getKey() + " x" + entry.getValue());
                }
            }
        }
    }
    
    // Getter pour la sauvegarde
    public Map<String, Integer> getObjets() {
        return objets;
    }
    
    // Setter pour le chargement
    public void setObjets(Map<String, Integer> objets) {
        this.objets = objets;
    }
}
