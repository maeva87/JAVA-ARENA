package projet;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Dresseur {
    // Attributs
    private String nom;
    private List<Monstre> equipe;
    private int monstreActifIndex;
    private HashMap<String, Integer> inventaire;
    private int credits;
    // Constructeur
    public Dresseur(String nom) {
        this.nom = nom;
        this.equipe = new ArrayList<>();
        this.monstreActifIndex = 0;
        this.inventaire = new HashMap<>();
        this.credits = 5;
    }
    // Getters
    public String getNom() {
        return nom;
    }

    public List<Monstre> getEquipe() {
        return equipe;
    }
    // Méthodes
    public Monstre getMonstreActif() {
    if (equipe.isEmpty()) {
        return null;
    }
    // Vérifier si le monstre actuel est KO
    if (equipe.get(monstreActifIndex).estKO()) {
        // Chercher le premier monstre disponible
        for (int i = 0; i < equipe.size(); i++) {
            if (!equipe.get(i).estKO()) {
                monstreActifIndex = i;
                return equipe.get(i);
            }
        }
        return null; // Tous KO
    }
    return equipe.get(monstreActifIndex);
    }
    public boolean changerMonstre (int index) {
        if (index < 0 || index >= equipe.size()) {
            System.out.println("Index invalide.");
            return false;
        }
        if (equipe.get(index).estKO()) {
            System.out.println("Le monstre choisi est KO et ne peut pas combattre.");
            return false;
        }
        monstreActifIndex = index;
        return true;
    }
    public boolean aDesMonstresDisponibles() {
        for (Monstre m : equipe) {
            if (!m.estKO()) {
                return true;
            }
        }
        return false;
    }
    public boolean ajouterMonstre(Monstre monstre) {
        if (equipe.size() >= 6) {
            System.out.println("L'équipe est déjà complète.");
            return false;
        }
        equipe.add(monstre);
        return true;
    }
    public void afficherEquipe() {
        System.out.println("Équipe de " + nom + " :");
        for (int i = 0; i < equipe.size(); i++) {
            Monstre m = equipe.get(i);
            String statut = m.estKO() ? " (KO)" : "";
            String actif = (i == monstreActifIndex) ? " [Actif]" : "";
            System.out.println(i  + ". " + m.getNom() + " - PV: " + m.getPvActuels() + "/" + m.getPvMax() + statut + actif);
        }
    }
    public void utiliserPotion(int indexMonstre) throws MonstreDejaEnPleineSanteException {
        if (!inventaire.containsKey("Potion") || inventaire.get("Potion") <= 0) {
            System.out.println("Vous n'avez pas de potion !");
            return;
        }
    
        Monstre cible = equipe.get(indexMonstre);
        if (cible.getPvActuels() == cible.getPvMax()) {
            throw new MonstreDejaEnPleineSanteException("Ce monstre a déjà tous ses PV !");
        }
    
        cible.soigner(20);
        inventaire.put("Potion", inventaire.get("Potion") - 1);
        System.out.println(cible.getNom() + " soigné de 20 PV !");
    }

    public void utiliserResurrection(int indexMonstre) {
        if (!inventaire.containsKey("Resurrection") || inventaire.get("Resurrection") <= 0) {
            System.out.println("Vous n'avez pas de potion de résurrection !");
            return;
        }
    
        Monstre cible = equipe.get(indexMonstre);
        if (!cible.estKO()) {
            System.out.println("Ce monstre n'est pas KO !");
            return;
        }
    
        cible.soigner(cible.getPvMax()); // Restaure tous les PV
        inventaire.put("Resurrection", inventaire.get("Resurrection") - 1);
        System.out.println(cible.getNom() + " est ressuscité !");
    }

    public void capturerMonstre(Monstre monstreSauvage) throws CaptureImpossibleException {
        if (!inventaire.containsKey("Pokeball") || inventaire.get("Pokeball") <= 0) {
            System.out.println("Vous n'avez pas de Pokeball !");
            return;
        }
    
        double pourcentagePV = (double) monstreSauvage.getPvActuels() / monstreSauvage.getPvMax() * 100;
        if (pourcentagePV > 30) {
            throw new CaptureImpossibleException("Le monstre a trop de PV ! (" + (int)pourcentagePV + "%)");
        }
    
        if (equipe.size() >= 6) {
            System.out.println("Équipe complète !");
            return;
        }
    
        equipe.add(monstreSauvage);
        inventaire.put("Pokeball", inventaire.get("Pokeball") - 1);
        System.out.println(monstreSauvage.getNom() + " capturé !");
    }

    public void ajouterCredit(int credit) {
        this.credits += credit;
        System.out.println("+" + credit + " crédits ! Total : " + this.credits);
    }

    public void acheterObjet(String nomObjet, int prix) {
        if (this.credits < prix) {
            System.out.println("Pas assez de crédits ! (Il vous manque " + (prix - credits) + ")");
            return;
        }
        this.credits -= prix;
        inventaire.put(nomObjet, inventaire.getOrDefault(nomObjet, 0) + 1);
        System.out.println(nomObjet + " acheté ! Crédits restants : " + this.credits);
    }
    public int getCredits() {
        return credits;
    }

    public void afficherInventaire() {
        System.out.println("\n=== Inventaire de " + nom + " ===");
        System.out.println("Crédits : " + credits);
        for (String objet : inventaire.keySet()) {
            System.out.println("- " + objet + " x" + inventaire.get(objet));
        }
    }
}