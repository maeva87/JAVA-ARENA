package projet;

import java.util.ArrayList;
import java.util.List;

public class Dresseur {
    // Attributs
    private String nom;
    private List<Monstre> equipe;
    private int monstreActifIndex;
    private Inventaire inventaire;
    
    // Constructeur
    public Dresseur(String nom) {
        this.nom = nom;
        this.equipe = new ArrayList<>();
        this.monstreActifIndex = 0;
        this.inventaire = new Inventaire();
    }
    // Getters
    public String getNom() {
        return nom;
    }

    public List<Monstre> getEquipe() {
        return equipe;
    }
    
    public Inventaire getInventaire() {
        return inventaire;
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
            System.out.println((i + 1)  + ". " + m.getNom() + " - PV: " + m.getPvActuels() + "/" + m.getPvMax() + statut + actif);
        }
    }
}