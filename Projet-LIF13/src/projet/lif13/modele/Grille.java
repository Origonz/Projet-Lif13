/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.lif13.modele;

/**
 *
 * @author billy
 */
public class Grille {
    private Case[][] tab;
    private int longueur;
    private int largeur;
    
    public Grille(int l,int L){
        if(l*L>1){
            largeur=l;
            longueur=L;
            tab = new Case[L][l];
            for(int i=0;i<longueur;i++){
                for(int j=0;j<largeur;j++){
                tab[i][j]= new Case();
                }
            }
        }else{
            System.out.println("Erreur lors de la saisie des données");
        }
    }

    public int getTab(int x,int y) {
        return tab[x][y].getId();
    }

    public void setTab(int x,int y,int val) {
        tab[x][y].setId(val);
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    
    @Override
    public String toString(){
        String result = "";
        for(int i=0;i<longueur;i++){
            for(int j=0;j<largeur;j++){
                result+=tab[i][j].getId()+" ";
            }
            result+="\n";
        }
        return result;
    }
    
    /*public void initpoints(int nb_points_pairs){
        if(nb_points_pairs>0){
            for(int i=1;i<nb_points_pairs+1;i++){
                randpoint(i);
                randpoint(i);
            }
        }
    }*/
    
    public boolean valide(int x,int y){
        if(x<longueur && y<largeur && x*y>=0)
            return tab[x][y].getId()==0;
        return false;
    }
    
    /*public void randpoint(int id){
        int x=0,y=0;
        do{
            x = (int)(random()*longueur);
            y = (int)(random()*largeur);
        }while(!valide(x,y));
        tab[x][y].setId(id);
    }*/
}
