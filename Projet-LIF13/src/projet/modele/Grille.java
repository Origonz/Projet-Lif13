package projet.modele;

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
    
    public void refreshTabGraphique(int x,int y) {
        tab[x][y].refreshGraphique();
    }
    
    
    /**
     * Rempli la grille de façon a avoir un chemin entre la case [x, y] et celle qui suit
     * @param x coord x de la premiere case
     * @param y coord y de la premiere case
     * @param direction où se trouve la case qui suit (0=^ / 1=> / 2=v / 3=<)
     */
    public void setMazePath(int x, int y, int direction) {
        switch (direction) {
            case 0:
                tab[x][y].setMazeValue(0, 1);
                tab[x][y-1].setMazeValue(2, 1);
                break;
            case 1:
                tab[x][y].setMazeValue(1, 1);
                tab[x+1][y].setMazeValue(3, 1);
                break;
            case 2:
                tab[x][y].setMazeValue(2, 1);
                tab[x][y+1].setMazeValue(0, 1);
                break;
            case 3:
                tab[x][y].setMazeValue(3, 1);
                tab[x-1][y].setMazeValue(1, 1);
                break;
            default:
                break;
        }
        
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
    
    public boolean valide(int x,int y){
        if (x<longueur && y <largeur && x*y >= 0)
            return tab[x][y].getId()==0;
        else 
            return false;
    }

    public int getTabGraphique(int x, int y) {
        return tab[x][y].getEtatGraphique();
    }

    public void resetMazePath() {
        for (Case[] tabx : tab) {
            for (Case taby : tabx) {
                for (int k = 0; k < 4; k++) {
                    taby.setMazeValue(k, 0);
                }
            }
        }
    }
}