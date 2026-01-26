package projet;

public class MonstreEau extends Monstre {
    public MonstreEau(String nom, int pvMax, int puissanceAttaque) {
        super(nom, Element.EAU, pvMax, puissanceAttaque);
    }
    
    @Override
    public int calculerDegats(Monstre cible) {
        int degats = getPuissanceAttaque();
        // Eau est efficace contre Feu
        if (cible.getElement() == Element.FEU) {
            degats *= 2;
            System.out.println("L'eau éteint le feu ! C'est super efficace !");
        }
        return degats;
    }
}