package projet;

import java.util.List;
import java.util.ArrayList;

public class Dresseur {
    // Attributs
    private String nom;
    private List<Monstre> equipe;
    private int monstreActifIndex;
    // Constructeur
    public Dresseur(String nom) {
        this.nom = nom;
        this.equipe = new ArrayList<>();
        this.monstreActifIndex = 0;
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
}