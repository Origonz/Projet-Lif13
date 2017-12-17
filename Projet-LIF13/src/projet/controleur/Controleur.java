package projet.controleur;

import static java.lang.Math.random;
import projet.vue.Case;
import projet.modele.Grille;
import projet.vue.FenGame;

/**
 *
 * @author billy
 */
public class Controleur {
    private Grille g;
    private FenGame game;
    private int vides;
    private int niveau;
    private boolean ligne;
    // 5 corespond au ligne non validé
    // 4 corespond au ligne validé
    
    private int lastCaseJoue[];
    private Case lastCaseVue;
    
    private int longueur;
    private int largeur;
    
    public Controleur(FenGame game){
        this.game = game;
        longueur = 4;
        largeur = 4;
        g=new Grille(longueur, largeur);
        vides = g.getLargeur()*g.getLongueur();
        ligne = false;
        niveau = 0;
        lastCaseJoue = new int[2];
        lastCaseJoue[0] = -1;
        lastCaseJoue[1] = -1;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }
    
    /**
     * A mettre dans le modele
     * @param x
     * @param y
     * @return 
     */
    public boolean jouerCoup(int x,int y){
        if(x*y>=0 && x<g.getLongueur() && y<g.getLargeur()) {
            if (g.valide(x, y) && adjacent(x, y)){
                g.setTab(x, y, 5);
                calcDessinChemin(x, y);
                g.refreshTabGraphique(x, y);
                g.refreshTabGraphique(lastCaseJoue[0], lastCaseJoue[1]);
                if (lastCaseVue != null) lastCaseVue.changeEtatAdmin(this);
                lastCaseJoue[0] = x;
                lastCaseJoue[1] = y;
                vides--;
                return true;
            } else if (g.getTab(x, y) == 1) {
                if (lastCaseJoue[0] == -1)  {
                    debut();
                    lastCaseJoue[0] = x;
                    lastCaseJoue[1] = y;
                } else if (adjacent(x, y)) {
                    calcDessinChemin(x, y);
                    g.refreshTabGraphique(lastCaseJoue[0], lastCaseJoue[1]);
                    if (lastCaseVue != null) lastCaseVue.changeEtatAdmin(this);
                    fin();
                }
            }
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
        game.afficherFin(vides == 0);
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
    
    public void restart(){
        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLargeur();j++){
                if(g.getTab(i, j)!=1){
                    vides++;
                    g.setTab(i, j, 0);
                }
            }
        }
        g.resetMazePath();
        lastCaseJoue[0] = -1;
        lastCaseJoue[1] = -1;
        lastCaseVue = null;
        game.restart();
    }
    
    public void reset() {
        g = new Grille(longueur, largeur);
        lastCaseJoue[0] = -1;
        lastCaseJoue[1] = -1;
        lastCaseVue = null;
        game.reset();
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
            }while((x+y)%2==0 && g.valide(x, y));
                g.setTab(x, y, 1);
            do{
                x = (int) (random()*g.getLongueur());
                y = (int) (random()*g.getLargeur());
            }while((x+y)%2==1 && !g.valide(x, y));
                g.setTab(x, y, 1);
        }
    }
    
    public int getIdGraphiqueCase(int x, int y) {
        return g.getTabGraphique(x, y);
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
        
        g.setMazePath(lastCaseJoue[0], lastCaseJoue[1], orientation);
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

    public void setLastCase(Case aThis) {
        lastCaseVue = aThis;
    }
    
    public Grille getGrille() {
        return g;
    }

    public boolean acceptSetDimensions(String longueur, String largeur) {
        int la, lo;
        la = this.largeur;
        lo = this.longueur;
        try {
            this.longueur = Integer.parseInt(longueur);
            this.largeur = Integer.parseInt(largeur);
            if (this.largeur <= 0 || this.longueur <= 0) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            this.largeur = la;
            this.longueur = lo;
            return false;
        }
    }
}