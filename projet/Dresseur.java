package projet;

import java.util.ArrayList;
import java.util.List;

public class Dresseur {
    // Attributs
    private String nom;
    private List<Monstre> equipe;
    private int monstreActifIndex;
    private Inventaire inventaire;
    private int credits;
    
    // Constructeur avec sélection aléatoire de 3 monstres
    public Dresseur(String nom) {
        this.nom = nom;
        this.equipe = new ArrayList<>();
        this.monstreActifIndex = 0;
        this.credits = 10; // Crédits de départ
        this.inventaire = new Inventaire();
        
        // Sélection aléatoire de 3 monstres lors de la création
        for (int i = 0; i < 3; i++) {
            Monstre monstreAleatoire = Bestiaire.creerMonstreAleatoire();
            if (monstreAleatoire != null) {
                equipe.add(monstreAleatoire);
            }
        }
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
    
    public int getCredits() {
        return credits;
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
    
    public boolean changerMonstre(int index) {
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
            System.out.println("L'équipe est déjà complète (6 monstres max).");
            return false;
        }
        equipe.add(monstre);
        return true;
    }
    
    public void afficherEquipe() {
        System.out.println("\n=== Équipe de " + nom + " ===");
        for (int i = 0; i < equipe.size(); i++) {
            Monstre m = equipe.get(i);
            String statut = m.estKO() ? " (KO)" : "";
            String actif = (i == monstreActifIndex) ? " [Actif]" : "";
            System.out.println((i + 1) + ". " + m.getNom() + " (" + m.getElement() + ") - PV: " + 
                            m.getPvActuels() + "/" + m.getPvMax() + statut + actif);
        }
    }
    
    // Utiliser une potion de soin
    public void utiliserPotion(int indexMonstre) throws MonstreFullPVException {
        if (!inventaire.possede("Potion")) {
            System.out.println("Vous n'avez pas de potion !");
            return;
        }
        
        if (indexMonstre < 0 || indexMonstre >= equipe.size()) {
            System.out.println("Index de monstre invalide !");
            return;
        }
    
        Monstre cible = equipe.get(indexMonstre);
        
        if (cible.estKO()) {
            System.out.println("Ce monstre est KO ! Utilisez un Elixir.");
            return;
        }
        
        if (cible.estFullPV()) {
            throw new MonstreFullPVException("Ce monstre a déjà tous ses PV !");
        }
    
        cible.soigner(20);
        inventaire.utiliserObjet("Potion");
        System.out.println(cible.getNom() + " soigné de 20 PV ! (" + cible.getPvActuels() + "/" + cible.getPvMax() + ")");
    }

    // Utiliser un Elixir (résurrection)
    public void utiliserElixir(int indexMonstre) {
        if (!inventaire.possede("Elixir")) {
            System.out.println("Vous n'avez pas d'Elixir !");
            return;
        }
        
        if (indexMonstre < 0 || indexMonstre >= equipe.size()) {
            System.out.println("Index de monstre invalide !");
            return;
        }
    
        Monstre cible = equipe.get(indexMonstre);
        if (!cible.estKO()) {
            System.out.println("Ce monstre n'est pas KO !");
            return;
        }
    
        cible.ressusciter(cible.getPvMax() / 2); // Restaure 50% des PV
        inventaire.utiliserObjet("Elixir");
        System.out.println(cible.getNom() + " est ressuscité avec " + cible.getPvActuels() + " PV !");
    }
    
    // Capturer un monstre sauvage avec un Filet
    public void capturerMonstre(Monstre monstreSauvage) throws CaptureImpossibleException {
        if (!inventaire.possede("Filet")) {
            System.out.println("Vous n'avez pas de Filet !");
            return;
        }
    
        double pourcentagePV = (double) monstreSauvage.getPvActuels() / monstreSauvage.getPvMax() * 100;
        if (pourcentagePV > 30) {
            throw new CaptureImpossibleException("Le monstre a trop de PV ! (" + (int)pourcentagePV + "% > 30%)");
        }
    
        if (equipe.size() >= 6) {
            System.out.println("Équipe complète (6 monstres maximum) !");
            return;
        }
    
        equipe.add(monstreSauvage);
        inventaire.utiliserObjet("Filet");
        System.out.println(monstreSauvage.getNom() + " capturé avec succès !");
    }

    // Ajouter des crédits (gagné en combat)
    public void ajouterCredit(int credit) {
        this.credits += credit;
        System.out.println("+" + credit + " crédits ! Total : " + this.credits);
    }

    // Acheter un objet en boutique
    public void acheterObjet(String nomObjet, int prix) {
        if (this.credits < prix) {
            System.out.println("Pas assez de crédits ! (Il vous manque " + (prix - credits) + ")");
            return;
        }
        this.credits -= prix;
        inventaire.ajouterObjet(nomObjet, 1);
        System.out.println(nomObjet + " acheté ! Crédits restants : " + this.credits);
    }

    // Afficher l'inventaire complet
    public void afficherInventaire() {
        System.out.println("\n=== Inventaire de " + nom + " ===");
        System.out.println("Crédits : " + credits);
        inventaire.afficher();
    }
}