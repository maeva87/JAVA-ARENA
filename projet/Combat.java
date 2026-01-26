package projet;

public class Combat {
    private Dresseur dresseur1;
    private Dresseur dresseur2;

    public Combat(Dresseur dresseur1, Dresseur dresseur2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
    }

    public int calculerDegats(Monstre attaquant, Monstre defenseur) {
        // Utilise le polymorphisme : chaque sous-classe a son propre calcul
        return attaquant.calculerDegats(defenseur);
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
        
        // Déterminer les labels (joueur vs adversaire)
        String labelAttaquant = (attaquant == dresseur1) ? "Votre " + monstreAttaquant.getNom() : "L'adversaire " + monstreAttaquant.getNom();
        String labelDefenseur = (defenseur == dresseur1) ? "votre " + monstreDefenseur.getNom() : "l'adversaire " + monstreDefenseur.getNom();
        
        // Calcul et application des dégâts
        int degats = calculerDegats(monstreAttaquant, monstreDefenseur);
        
        System.out.println(labelAttaquant + " attaque " + labelDefenseur + " !");
        monstreDefenseur.recevoirDegats(degats);
        
        if (monstreDefenseur.estKO()) {
            System.out.println(labelDefenseur + " perd " + degats + " PV et est KO !");
        } else {
            System.out.println(labelDefenseur + " perd " + degats + " PV ! (" + 
            monstreDefenseur.getPvActuels() + "/" + monstreDefenseur.getPvMax() + " PV restants)");
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
}