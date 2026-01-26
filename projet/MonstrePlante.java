package projet;

public class MonstrePlante extends Monstre {
    public MonstrePlante(String nom, int pvMax, int puissanceAttaque) {
        super(nom, Element.PLANTE, pvMax, puissanceAttaque);
    }
}