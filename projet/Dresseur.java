package projet;

import java.util.List;

public class Dresseur {
    private String nom;
    private List<Monstre> equipe;
    int monstreActifIndex = 0;

    public Dresseur(String nom, List<Monstre> equipe) {
        this.nom = nom;
        this.equipe = equipe;
    }

    public String getNom() {
        return nom;
    }

    public Monstre getMonstreActif() {
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
}