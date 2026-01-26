package projet;

/**
 * Représente un objet utilisable dans le jeu
 */
public class Objet {
    private String nom;
    private String description;
    private int prix;
    private TypeObjet type;
    
    public enum TypeObjet {
        POTION,      // Soigne un monstre
        ELIXIR,      // Ressuscite un monstre KO
        CAPTURE      // Capture un monstre sauvage
    }
    
    public Objet(String nom, String description, int prix, TypeObjet type) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getPrix() {
        return prix;
    }
    
    public TypeObjet getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return nom + " - " + description + " (" + prix + " crédits)";
    }
}
