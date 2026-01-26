package projet;

public abstract class Monstre {
    private String nom;
    private int pvActuels;
    private int pvMax;
    private int puissanceAttaque;
    private Element element;

    public Monstre(String nom, Element element, int pvMax, int puissanceAttaque) {
        this.nom = nom;
        this.element = element;
        this.pvMax = pvMax;
        this.pvActuels = pvMax;
        this.puissanceAttaque = puissanceAttaque;
    }

    public String getNom() { return nom; }
    public int getPvActuels() { return pvActuels; }
    public int getPvMax() { return pvMax; }
    public int getPuissanceAttaque() { return puissanceAttaque; }
    public Element getElement() { return element; }

    public boolean estKO() { return pvActuels <= 0; }
    
    public boolean estFullPV() { return pvActuels >= pvMax; }

    public void recevoirDegats(int degats) {
        pvActuels -= degats;
        if (pvActuels < 0) pvActuels = 0;
    }

    public void soigner(int soin) throws MonstreFullPVException {
        if (estFullPV()) {
            throw new MonstreFullPVException(nom + " a déjà tous ses PV !");
        }
        pvActuels += soin;
        if (pvActuels > pvMax) pvActuels = pvMax;
    }
    
    // Ressusciter un monstre KO (ne lance pas d'exception)
    public void ressusciter(int pvRestaures) {
        if (estKO()) {
            pvActuels = pvRestaures;
            if (pvActuels > pvMax) pvActuels = pvMax;
        }
    }
    
    // Méthode polymorphique : calcule les dégâts infligés à une cible
    public int calculerDegats(Monstre cible) {
        int degats = puissanceAttaque;
        if (element.estEfficaceContre(cible.getElement())) {
            degats *= 2; // Double dégâts si efficace
        }
        return degats;
    }

    @Override
    public String toString() {
        return nom + " (" + element + ") - PV: " + pvActuels + "/" + pvMax + " - Attaque: " + puissanceAttaque;
    }
}