/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import projet.controleur.Controleur;
import projet.modele.Grille;

/** 
 * Element graphique du jeu.
 * @author Zonthem
 */
public class Case extends ImageView {
    
    private int X_correspondant;
    private int Y_correspondant;
    private ArrayList<Image> images;
    
    /**
     * Unique constructeur, appelée seulement à la création du jeu.
     * (possiblement refaire une partie)
     * @param type valeur de la case à la création (0=case cliquable  ?=case arrivée)
     * @param x correspondance X dans la matrice de controle
     * @param y correspondance Y dans la matrice de controle
     */
    public Case(int type, int x, int y, int ratio) {
        super(new Image("projet/ressources/defaut.png", 100*4/ratio, 100*4/ratio, false, false));
        X_correspondant = x;
        Y_correspondant = y;
        if (type == 0) {
            try {
                images = new ArrayList<>();
                images.add(new Image("projet/ressources/defaut.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/horizontal.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/vertical.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/topleft.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/botleft.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/topright.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/botright.png", 100*4/ratio, 100*4/ratio, false, false));
            } catch (Exception e) {
                System.err.println(e.getMessage() + " - " + images);
            }
        } else {
            try {
                images = new ArrayList<>();
                images.add(new Image("projet/ressources/b1.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/b2.png", 100*4/ratio, 100*4/ratio, false, false));
                images.add(new Image("projet/ressources/b3.png", 100*4/ratio, 100*4/ratio, false, false));
                setImage(images.get(type-1));
            } catch (Exception e) {
                System.err.println(e.getMessage() + " - " + images);
            }
        }  
    }
    
    public void setCoord(int x, int y) {
        X_correspondant = x;
        Y_correspondant = y;
    }
    
    public int[] getCoord() {
        int tab[] = {X_correspondant, Y_correspondant};
        return (tab);
    }
    
    public void changeEtat(Controleur c) {
        if (c.jouerCoup(X_correspondant, Y_correspondant)) {
            c.getGrille().setLastCase(this);
            setImage(images.get(c.getGrille().getTab(X_correspondant, Y_correspondant)));
        }
    }

    void chargeDefaut() {
        setImage(images.get(0));
    }

    public void changeEtatAdmin(Grille g) {
        setImage(images.get(g.getTabGraphique(X_correspondant, Y_correspondant)));
    }
}
