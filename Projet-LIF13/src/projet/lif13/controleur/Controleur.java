/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.lif13.controleur;

import static java.lang.Math.random;
import projet.lif13.modele.Grille;

/**
 *
 * @author billy
 */
public class Controleur {
    private Grille g;
    private int vides;
    private int niveau;
    private boolean ligne;
    private int xp,yp;
    // 5 corespond au ligne non validé
    // 4 corespond au ligne validé
    
    public Controleur(Grille a){
        g=a;
        vides = a.getLargeur()*a.getLongueur();
        ligne = false;
        niveau = 0;
        xp=yp=-1;
    }
    
    public Controleur(){
        g = new Grille(4,4);
        vides = 16;
        ligne = false;
        niveau = 0;
        xp=yp=-1;
    }
    
    public boolean jouerCoup(int x,int y){
        int cox,coy;
        cox = Math.abs(x-xp);
        coy = Math.abs(y-yp);
        if(x*y>=0 && x<g.getLongueur() && y<g.getLargeur() && g.valide(x, y) && (cox+coy==1)) {
            g.setTab(x, y, 5);
            vides--;
            xp=x;
            yp=y;
            return true;
        }
        return false;
    }
    
    public void initialisation(int lvl){
        vides--;
        vides--;
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
    
    public void annuler(){
        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLargeur();j++){
                if(g.getTab(i, j)==5){
                    vides++;
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
   
    public void debut(){
        ligne = true;
    }
    
    public void fin(){
        if(ligne){
            ligne = false;
            valider();
            victoire();
        }
    }
    
    public void resetall(){
        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLargeur();j++){   
                    g.setTab(i, j, 0);
            }
        }   
    }
    
    public void resetwithpoints(){
        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLargeur();j++){ 
                if(g.getTab(i, j)!=1){
                    g.setTab(i, j, 0);
                }
            }
        }   
    }
    
    
    public void placementpoints(){ //calculer quand les points sont valide
        vides--;
        vides--;
        int x,y;
        if(g.getLargeur()%2==1){
            for(int i=0;i<2;i++){
                do{
                    x = (int) (random()*g.getLongueur());
                    y = (int) (random()*g.getLargeur());
                }while((x+y)%2==0 && g.valide(x, y));
                g.setTab(x, y, 1);
            }
        }else{
            do{
                x = (int) (random()*g.getLongueur());
                y = (int) (random()*g.getLargeur());
            }while(!(g.valide(x, y) && (x+y)%2==0));
            g.setTab(x, y, 1);
            do{
                x = (int) (random()*g.getLongueur());
                y = (int) (random()*g.getLargeur());
            }while(!(g.valide(x, y) && (x+y)%2==1));
            g.setTab(x, y, 1);
        }
    }
    
}
