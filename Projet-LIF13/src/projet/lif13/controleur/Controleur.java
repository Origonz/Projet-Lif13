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
    private int vides;
    private int niveau;
    // 5 corespond au ligne non validé
    // 4 corespond au ligne validé
    
    public Controleur(Grille a){
        g=a;
        vides = a.getLargeur()*a.getLongueur();
    }
    
    public Controleur(){
        g = new Grille(4,4);
        vides = 16;
    }
    
    public boolean jouerCoup(int x,int y){
        if(x*y>=0 && x<g.getLongueur() && y<g.getLargeur() && g.valide(x, y)){
            g.setTab(x, y, 5);
            vides--;
            return true;
        }
        return false;
    }
    
    public void initialisation(int lvl){
        niveau=lvl;
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
    
    public void affichage(){
        System.out.println(g.toString());
        System.out.println("Cases vides : "+vides);
    }
    
    public void victoire(){
        if(vides==0)
            System.out.println("Victoire !!!");
    }
    
    public void reset(){
        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLargeur();j++){
                if(g.getTab(i, j)==5){
                    g.setTab(i, j, 0);
                }
            }
        }
    }
    
    public void valider(){
        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLargeur();j++){
                if(g.getTab(i, j)==5){
                    g.setTab(i, j, 4);
                }
            }
        }
    }
   
}
