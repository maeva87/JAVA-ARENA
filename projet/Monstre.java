package projet;

public class Monstre {
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

    public void recevoirDegats(int degats) {
        pvActuels -= degats;
        if (pvActuels < 0) pvActuels = 0;
    }

    public void soigner(int soin) {
        pvActuels += soin;
        if (pvActuels > pvMax) pvActuels = pvMax;
    }

    @Override
    public String toString() {
        return nom + " (" + element + ") - PV: " + pvActuels + "/" + pvMax + " - Attaque: " + puissanceAttaque;
    }
}
