package projet;

public class Bestiaire {
    
    public static Monstre creerMonstre(String type) {
        switch(type.toLowerCase()) {
            // Monstres EAU
            case "aqualis":
                return new MonstreEau("Aqualis", 40, 5);
            case "hydrax":
                return new MonstreEau("Hydrax", 45, 4);
            case "tortank":
                return new MonstreEau("Tortank", 50, 6);
            
            // Monstres FEU
            case "pyroxis":
                return new MonstreFeu("Pyroxis", 40, 5);
            case "flammix":
                return new MonstreFeu("Flammix", 35, 7);
            case "salamandre":
                return new MonstreFeu("Salamandre", 38, 6);
            
            // Monstres PLANTE
            case "terragon":
                return new MonstrePlante("Terragon", 40, 5);
            case "verdax":
                return new MonstrePlante("Verdax", 50, 3);
            case "floramis":
                return new MonstrePlante("Floramis", 42, 5);
            
            default:
                System.out.println("Monstre inconnu : " + type);
                return null;
        }
    }
    
    // Méthode pour créer un monstre aléatoire
    public static Monstre creerMonstreAleatoire() {
        String[] monstres = {
            "aqualis", "hydrax", "tortank",
            "pyroxis", "flammix", "salamandre",
            "terragon", "verdax", "floramis"
        };
        
        int index = (int)(Math.random() * monstres.length);
        return creerMonstre(monstres[index]);
    }
}