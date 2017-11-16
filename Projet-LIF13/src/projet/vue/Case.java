/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** 
 * Element graphique du jeu.
 * @author Zonthem
 */
public class Case extends ImageView {
    
    private int X_correspondant;
    private int Y_correspondant;
    private int etat;
    private ArrayList<Image> images;
    
    /**
     * Unique constructeur, appelée seulement à la création du jeu.
     * (possiblement refaire une partie)
     * @param type valeur de la case à la création (0=case cliquable  ?=case arrivée)
     * @param x correspondance X dans la matrice de controle
     * @param y correspondance Y dans la matrice de controle
     */
    public Case(int type, int x, int y) {
        super(new Image("projet/ressources/defaut.png"));
        X_correspondant = x;
        Y_correspondant = y;
        if (type == 0) {
            etat = 0;
            try {
                images = new ArrayList<>();
                images.add(new Image("projet/ressources/defaut.png"));
                images.add(new Image("projet/ressources/horizontal.png"));
                images.add(new Image("projet/ressources/vertical.png"));
                images.add(new Image("projet/ressources/topleft.png"));
                images.add(new Image("projet/ressources/botleft.png"));
                images.add(new Image("projet/ressources/topright.png"));
                images.add(new Image("projet/ressources/botright.png"));
            } catch (Exception e) {
                System.err.println(e.getMessage() + " - " + images);
            }
        } else {
            etat = type;
            try {
                images = new ArrayList<>();
                images.add(new Image("projet/ressources/b1.png"));
                images.add(new Image("projet/ressources/b2.png"));
                images.add(new Image("projet/ressources/b3.png"));
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
    
    public int getEtat() {
        return etat;
    }
    
    public void changeEtat() {
        //if jouerCoup
        if (etat < 6) etat++;
        else if (etat == 6) etat = 0;
        //autres cas : ne pas changer l'etat
        
        setImage(images.get(etat));
    }

    public void reset() {
        //if resetable
        etat = 0;
        setImage(images.get(etat));
    }
}
