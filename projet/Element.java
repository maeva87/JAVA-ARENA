package projet;

public enum Element {
    FEU,
    EAU,
    PLANTE;

    public boolean estEfficaceContre(Element autre) {
        switch (this) {
            case FEU:
                return autre == PLANTE;
            case EAU:
                return autre == FEU;
            case PLANTE:
                return autre == EAU;
            default:
                return false;
        }
    }
}
