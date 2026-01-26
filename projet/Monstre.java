package projet;

<<<<<<< HEAD
public class Monstre {
=======
public abstract class Monstre {
>>>>>>> main
    private String nom;
    private int pvActuels;
    private int pvMax;
    private int puissanceAttaque;
    private Element element;

<<<<<<< HEAD
    public Monstre(String nom, Element element, int pvMax, int puissanceAttaque) {
=======
    // Constructeur protégé (utilisable seulement par les classes enfants)
    protected Monstre(String nom, Element element, int pvMax, int puissanceAttaque) {
>>>>>>> main
        this.nom = nom;
        this.element = element;
        this.pvMax = pvMax;
        this.pvActuels = pvMax;
        this.puissanceAttaque = puissanceAttaque;
    }
<<<<<<< HEAD

    public String getNom() { return nom; }
    public int getPvActuels() { return pvActuels; }
    public int getPvMax() { return pvMax; }
    public int getPuissanceAttaque() { return puissanceAttaque; }
    public Element getElement() { return element; }
=======
    // Getters
    public String getNom() {
        return nom;
    }
>>>>>>> main

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
