package projet;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private Map<String, Integer> objets;
    
    public Inventaire() {
        this.objets = new HashMap<>();
    }
    
    public void ajouterObjet(String nom, int quantite) {
        objets.put(nom, objets.getOrDefault(nom, 0) + quantite);
    }
    
    public boolean utiliserObjet(String nom) {
        Integer quantite = objets.get(nom);
        if (quantite == null || quantite <= 0) {
            return false;
        }
        objets.put(nom, quantite - 1);
        if (objets.get(nom) == 0) {
            objets.remove(nom);
        }
        return true;
    }
    
    public int getQuantite(String nom) {
        return objets.getOrDefault(nom, 0);
    }
    
    public void afficher() {
        System.out.println("\n=== INVENTAIRE ===");
        if (objets.isEmpty()) {
            System.out.println("Inventaire vide !");
        } else {
            for (Map.Entry<String, Integer> entry : objets.entrySet()) {
                System.out.println("- " + entry.getKey() + " x" + entry.getValue());
            }
        }
    }
    
    public Map<String, Integer> getObjets() {
        return objets;
    }
    
    public void setObjets(Map<String, Integer> objets) {
        this.objets = objets;
    }
}
