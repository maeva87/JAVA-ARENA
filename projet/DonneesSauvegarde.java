package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe pour stocker les données chargées depuis la sauvegarde
public class DonneesSauvegarde {
    public String nomDresseur;
    public int credits;
    public List<Monstre> monstres;
    public Map<String, Integer> inventaire;
    
    public DonneesSauvegarde() {
        this.monstres = new ArrayList<>();
        this.inventaire = new HashMap<>();
    }
}
