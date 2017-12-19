package projet.controleur;

import projet.modele.Grille;
import projet.vue.FenGame;

/**
 *
 * @author billy
 */
public class Controleur {
    private Grille g;
    private FenGame game;
    
    public Controleur(FenGame game){
        this.game = game;
        g=new Grille(4, 4, 0);
    }
    
    public boolean jouerCoup(int x,int y){
        boolean a;
        a = g.jouerCoup(x, y);
        game.afficherFin(g.getVides() == 0);
        return a;
    }

    public void setLongueur(int longueur) {
        g.setLongueur(longueur);
    }

    public void setLargeur(int largeur) {
        g.setLargeur(largeur);
    }

    public int getLongueur() {
        return g.getLongueur();
    }

    public int getLargeur() {
        return g.getLargeur();
    }  
    
    public Grille getGrille() {
        return g;
    }

    public boolean acceptSetDimensions(String longueur, String largeur) {
        int la, lo;
        la = getLargeur();
        lo = getLongueur();
        try {
            setLongueur(Integer.parseInt(longueur));
            setLargeur(Integer.parseInt(largeur));
            if (getLargeur() <= 0 || getLongueur() <= 0) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            setLargeur(la);
            setLongueur(lo);
            return false;
        }
    }
    
    public void reset(int lvl) {
        g = new Grille(getLongueur(), getLargeur(), lvl);
        game.reset();
    }
    
    public void restart(){
        g.restart();
        game.restart();
    }
}