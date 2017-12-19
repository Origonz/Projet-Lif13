package projet.modele;

import static java.lang.Math.random;

/**
 *
 * @author billy
 */
public class Grille {
    private Case[][] tab;
    private int longueur;
    private int largeur;
    private int lastCaseJoue[];
    private projet.vue.Case lastCaseVue;
    private boolean ligne;
    private int vides;
    
    
    public Grille(int l,int L,int lvl){
        if(l*L>1){
            largeur=l;
            longueur=L;
            tab = new Case[L][l];
            for(int i=0;i<longueur;i++){
                for(int j=0;j<largeur;j++){
                tab[i][j]= new Case();
                }
            }
            lastCaseJoue = new int[2];
            lastCaseJoue[0] = -1;
            lastCaseJoue[1] = -1;
            lastCaseVue = null;
            ligne = false;
            vides = largeur*longueur;
            placementpoints(lvl);
        }else{
            System.out.println("Erreur lors de la saisie des données");
        }
    }

    public int getTab(int x,int y) {
        return tab[x][y].getId();
    }
    
    public void setLastCase(projet.vue.Case c){
        lastCaseVue = c;
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
    
    private void placementpoints(int lvl){ //calculer quand les points sont valide
        int x,y;
        vides = vides - 2;
        if(lvl==0){
        if(largeur%2==1){
            for(int i=0;i<2;i++){
                do{
                    x = (int) (random()*longueur);
                    y = (int) (random()*largeur);
                }while((x+y)%2==0 && valide(x, y));
                tab[x][y]=new Case(1);
            }
        }else{
            do{
                x = (int) (random()*longueur);
                y = (int) (random()*largeur);
            }while((x+y)%2==0 && valide(x, y));
                tab[x][y]=new Case(1);
            do{
                x = (int) (random()*longueur);
                y = (int) (random()*largeur);
            }while((x+y)%2==1 && !valide(x, y));
                tab[x][y]=new Case(1);
        }
        }
        niveau(lvl);
    }
    
    public void niveau(int lvl){
        switch (lvl) {
            case 2:  tab[0][0]=new Case(1);
                     tab[0][1]=new Case(1);
                     tab[0][3]=new Case(2);
                     tab[2][1]=new Case(2);
                     break;
            case 3:  tab[0][0]=new Case(1);
                     tab[0][1]=new Case(1);
                     tab[0][3]=new Case(2);
                     tab[2][1]=new Case(2);
                     tab[1][2]=new Case(3);
                     tab[1][0]=new Case(3);
                     break;
            case 1:  tab[2][3]=new Case(1);
                     tab[3][1]=new Case(1);
                     break;
            default: ;
                     break;
        }
    }
    
    public boolean jouerCoup(int x,int y){
        if(x*y>=0 && x<longueur && y<largeur) {
            if (valide(x, y) && adjacent(x, y)){
               tab[x][y]=new Case(1);
                calcDessinChemin(x, y);
                refreshTabGraphique(x, y);
                refreshTabGraphique(lastCaseJoue[0], lastCaseJoue[1]);
                if (lastCaseVue != null) lastCaseVue.changeEtatAdmin(this);
                lastCaseJoue[0] = x;
                lastCaseJoue[1] = y;
                vides--;
                return true;
            } else if (tab[x][y] == new Case(1)) {
                if (lastCaseJoue[0] == -1)  {
                    debut();
                    lastCaseJoue[0] = x;
                    lastCaseJoue[1] = y;
                } else if (adjacent(x, y)) {
                    calcDessinChemin(x, y);
                    refreshTabGraphique(lastCaseJoue[0], lastCaseJoue[1]);
                    if (lastCaseVue != null) lastCaseVue.changeEtatAdmin(this);
                    fin();
                }
            }
        }
        return false;
    }
    
    private void calcDessinChemin(int x, int y) {
        //orentation de la case par rapport à la précédente
        int orientation;
        
        if (x == lastCaseJoue[0]) {
            if (y == lastCaseJoue[1] +1) {
                orientation = 2;
            } else if (y == lastCaseJoue[1] -1) {
                orientation = 0;
            } else orientation = -1;
            
        } else if (y == lastCaseJoue[1]) {
            if (x == lastCaseJoue[0] -1) {
                orientation = 3;
            } else if (x == lastCaseJoue[0] +1) {
                orientation = 1;
            } else orientation = -1;
            
        } else orientation = -1;
        
        //ici, orientation définit le sens de la relation : 
        //où est la case cliquée par rapport à la precedente
        
        setMazePath(lastCaseJoue[0], lastCaseJoue[1], orientation);
    }

    private boolean adjacent(int x, int y) {
        if (x == lastCaseJoue[0]) {
            return y == lastCaseJoue[1] - 1 || y == lastCaseJoue[1] + 1;
        } else if (y == lastCaseJoue[1]) {
            return x == lastCaseJoue[0] - 1 || x == lastCaseJoue[0] + 1;
        } else {
            return false;
        }
    }
    
     public void debut(){
        ligne = true;
    }
    
    public void fin(){
        if(ligne){
            ligne = false;
            valider();
        }
    }
    
    public int getVides(){
        return vides;
    }
    
    public void annuler(){
        for(int i=0;i<longueur;i++){
            for(int j=0;j<largeur;j++){
                if(tab[i][j].getId()==5){
                    vides++;
                    tab[i][j]= new Case(0);
                }
            }
        }
    }
    
    public void valider(){
        for(int i=0;i<longueur;i++){
            for(int j=0;j<largeur;j++){
                if(tab[i][j].getId()==5){
                    tab[i][j]= new Case(4);
                }
            }
        }
    }
    
    public void restart(){
        for(int i=0;i<longueur;i++){
            for(int j=0;j<largeur;j++){
                if(tab[i][j].getId()!=1){
                    vides++;
                    tab[i][j]= new Case(0);
                }
            }
        }
        resetMazePath();
    }
}