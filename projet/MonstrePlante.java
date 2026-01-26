package projet;

public class MonstrePlante extends Monstre {
    public MonstrePlante(String nom, int pvMax, int puissanceAttaque) {
        super(nom, Element.PLANTE, pvMax, puissanceAttaque);
    }
    
    @Override
    public int calculerDegats(Monstre cible) {
        int degats = getPuissanceAttaque();
        // Plante est efficace contre Eau
        if (cible.getElement() == Element.EAU) {
            degats *= 2;
            System.out.println("La plante absorbe l'eau ! C'est super efficace !");
        }
        return degats;
    }
}