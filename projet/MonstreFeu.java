package projet;

public class MonstreFeu extends Monstre {
    public MonstreFeu(String nom, int pvMax, int puissanceAttaque) {
        super(nom, Element.FEU, pvMax, puissanceAttaque);
    }
    
    @Override
    public int calculerDegats(Monstre cible) {
        int degats = getPuissanceAttaque();
        // Feu est efficace contre Plante
        if (cible.getElement() == Element.PLANTE) {
            degats *= 2;
            System.out.println("Le feu brûle la plante ! C'est super efficace !");
        }
        return degats;
    }
}