<<<<<<< HEAD
package projet;

public class Combat {
    
=======
package projet;

public class Combat {
    private Dresseur dresseur1;
    private Dresseur dresseur2;

    public Combat(Dresseur dresseur1, Dresseur dresseur2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
    }

    public int calculerDegats(Monstre attaquant, Monstre defenseur) {
        int degats = attaquant.getPuissanceAttaque();
        
        if (attaquant.getElement().estEfficaceContre(defenseur.getElement())) {
            degats *= 2;
            System.out.println("C'est super efficace !");
        }
        
        return degats;
    }

    // Méthode avec gestion des exceptions
    public void attaquer(Dresseur attaquant, Dresseur defenseur) 
            throws MonstreKOException, CibleDejaKOException {
        
        Monstre monstreAttaquant = attaquant.getMonstreActif();
        Monstre monstreDefenseur = defenseur.getMonstreActif();
        
        // Vérification avec exception si monstre attaquant KO
        if (monstreAttaquant == null || monstreAttaquant.estKO()) {
            throw new MonstreKOException("Impossible d'attaquer : votre monstre est KO !");
        }
        
        // Vérification avec exception si cible KO
        if (monstreDefenseur == null || monstreDefenseur.estKO()) {
            throw new CibleDejaKOException("Impossible d'attaquer : la cible est déjà KO !");
        }
        
        // Calcul et application des dégâts
        int degats = calculerDegats(monstreAttaquant, monstreDefenseur);
        
        System.out.println(monstreAttaquant.getNom() + " attaque " + 
        monstreDefenseur.getNom() + " !");
        monstreDefenseur.recevoirDegats(degats);
        System.out.println(monstreDefenseur.getNom() + " perd " + degats + " PV !");
        
        if (monstreDefenseur.estKO()) {
            System.out.println(monstreDefenseur.getNom() + " est KO !");
        } else {
            System.out.println(monstreDefenseur.getNom() + " : " + 
            monstreDefenseur.getPvActuels() + "/" + 
            monstreDefenseur.getPvMax() + " PV");
        }
    }

    public boolean combatTermine() {
        return !dresseur1.aDesMonstresDisponibles() || 
        !dresseur2.aDesMonstresDisponibles();
    }

    public Dresseur getGagnant() {
        if (dresseur1.aDesMonstresDisponibles()) {
            return dresseur1;
        } else if (dresseur2.aDesMonstresDisponibles()) {
            return dresseur2;
        }
        return null;
    }

    public void afficherEtatCombat() {
        System.out.println("\n========== ÉTAT DU COMBAT ==========");
        System.out.println(dresseur1.getNom() + " : " + dresseur1.getMonstreActif());
        System.out.println(dresseur2.getNom() + " : " + dresseur2.getMonstreActif());
        System.out.println("====================================\n");
    }
>>>>>>> main
}