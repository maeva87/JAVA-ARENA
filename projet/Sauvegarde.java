package projet;

import java.io.*;
import java.util.Map;

public class Sauvegarde {
    private static final String FICHIER_SAUVEGARDE = "sauvegarde.csv";
    
    // Sauvegarder la partie (équipe + inventaire + crédits)
    public static void sauvegarder(Dresseur joueur, int credits) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FICHIER_SAUVEGARDE))) {
            // Ligne 1: Nom du dresseur et crédits
            writer.println("DRESSEUR;" + joueur.getNom() + ";" + credits);
            
            // Lignes monstres: MONSTRE;NOM;ELEMENT;PV_ACTUELS;PV_MAX;PUISSANCE
            for (Monstre m : joueur.getEquipe()) {
                writer.println("MONSTRE;" + m.getNom() + ";" + m.getElement() + ";" + 
                              m.getPvActuels() + ";" + m.getPvMax() + ";" + m.getPuissanceAttaque());
            }
            
            // Lignes inventaire: ITEM;NOM;QUANTITE
            for (Map.Entry<String, Integer> entry : joueur.getInventaire().getObjets().entrySet()) {
                if (entry.getValue() > 0) {
                    writer.println("ITEM;" + entry.getKey() + ";" + entry.getValue());
                }
            }
            
            System.out.println("Partie sauvegardée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
    
    // Charger une partie sauvegardée
    public static DonneesSauvegarde charger() {
        File fichier = new File(FICHIER_SAUVEGARDE);
        if (!fichier.exists()) {
            System.out.println("Aucune sauvegarde trouvée.");
            return null;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_SAUVEGARDE))) {
            DonneesSauvegarde donnees = new DonneesSauvegarde();
            String ligne;
            
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                
                switch (parts[0]) {
                    case "DRESSEUR":
                        donnees.nomDresseur = parts[1];
                        donnees.credits = Integer.parseInt(parts[2]);
                        break;
                        
                    case "MONSTRE":
                        String nom = parts[1];
                        Element element = Element.valueOf(parts[2]);
                        int pvActuels = Integer.parseInt(parts[3]);
                        int pvMax = Integer.parseInt(parts[4]);
                        int puissance = Integer.parseInt(parts[5]);
                        
                        // Créer le monstre avec ses stats
                        Monstre monstre = new Monstre(nom, element, pvMax, puissance) {};
                        // Ajuster les PV actuels (infliger les dégâts subis)
                        int degatsSubis = pvMax - pvActuels;
                        if (degatsSubis > 0) {
                            monstre.recevoirDegats(degatsSubis);
                        }
                        donnees.monstres.add(monstre);
                        break;
                        
                    case "ITEM":
                        String nomItem = parts[1];
                        int quantite = Integer.parseInt(parts[2]);
                        donnees.inventaire.put(nomItem, quantite);
                        break;
                }
            }
            
            System.out.println("Partie chargée avec succès !");
            return donnees;
            
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
    }
    
    // Vérifier si une sauvegarde existe
    public static boolean sauvegardeExiste() {
        return new File(FICHIER_SAUVEGARDE).exists();
    }
    
    // Supprimer la sauvegarde (nouvelle partie écrase l'ancienne)
    public static void supprimerSauvegarde() {
        File fichier = new File(FICHIER_SAUVEGARDE);
        if (fichier.exists()) {
            fichier.delete();
        }
    }
}
