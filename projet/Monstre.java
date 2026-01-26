package projet;

public abstract class Monstre {
    private String nom;
    private int pvActuels;
    private int pvMax;
    private int puissanceAttaque;
    private Element element;

    // Constructeur protégé (utilisable seulement par les classes enfants)
    protected Monstre(String nom, Element element, int pvMax, int puissanceAttaque) {
        this.nom = nom;
        this.element = element;
        this.pvMax = pvMax;
        this.pvActuels = pvMax;
        this.puissanceAttaque = puissanceAttaque;
    }
    // Getters
    public String getNom() {
        return nom;
    }

    public int getPvActuels() {
        return pvActuels;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getPuissanceAttaque() {
        return puissanceAttaque;
    }

    public Element getElement() {
        return element;
    }

    // Méthodes importantes pour le combat
    public boolean estKO() {
        return pvActuels <= 0;
    }

    public void recevoirDegats(int degats) {
        pvActuels -= degats;
        if (pvActuels < 0) {
            pvActuels = 0;  // Les PV ne peuvent pas être négatifs
        }
    }

    public void soigner(int soin) {
        pvActuels += soin;
        if (pvActuels > pvMax) {
            pvActuels = pvMax;  // Ne peut pas dépasser les PV max
        }
    }

    // Méthode pour afficher les infos du monstre (utile pour débugger)
    @Override
    public String toString() {
        return nom + " (" + element + ") - PV: " + pvActuels + "/" + pvMax + " - Attaque: " + puissanceAttaque;
    }
}