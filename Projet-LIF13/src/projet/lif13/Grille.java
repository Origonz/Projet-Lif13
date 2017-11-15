/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.lif13;

/**
 *
 * @author billy
 */
public class Grille {
    private Case[][] tab;
    private int longueur;
    private int largeur;
    
    Grille(int l,int L){
        if(l*L>1){
            longueur=l;
            largeur=L;
            tab = new Case[l][L];
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
}
