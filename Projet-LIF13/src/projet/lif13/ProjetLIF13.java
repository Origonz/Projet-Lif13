/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.lif13;

import projet.lif13.controleur.Controleur;
import projet.lif13.modele.Grille;

/**
 *
 * @author billy
 */
public class ProjetLIF13 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Controleur c = new Controleur();
       c.placementpoints();
       c.affichage();
       
    }
    
}
