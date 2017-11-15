/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.lif13.controleur;

import projet.lif13.modele.Grille;

/**
 *
 * @author billy
 */
public class Controleur {
    private Grille g;
    
    public Controleur(Grille a){
        g=a;
    }
    
    public Controleur(){
        g = new Grille(4,4);
    }
    
    public boolean jouerCoup(int x,int y){
        if(x*y>=0 && x<g.getLongueur() && y<g.getLargeur() && g.valide(x, y)){
            g.setTab(x, y, 10);
            return true;
        }
        return false;
    }
    
    public void initialisation(int lvl){
        switch (lvl) {
            case 1:  g.setTab(0, 0, lvl);
                     g.setTab(0, 1, lvl);
                     break;
            case 3:  g.setTab(0, 0, lvl);
                     g.setTab(2, 1, lvl);
                     break;
            case 2:  g.setTab(2, 3, lvl);
                     g.setTab(1, 3, lvl);
                     break;
            default: ;
                     break;
        }
    }
    
    public void affiche(){
        System.out.println(g.toString());
    }
    
}
