package projet;

public enum Element {
    FEU,
    EAU,
    PlANTE;

    public boolean estEfficaceContre(Element autre) {
        switch (this) {
            case FEU:
                return autre == PlANTE;
            case EAU:
                return autre == FEU;
            case PlANTE:
                return autre == EAU;
            default:
                return false;
        }
    }
}
