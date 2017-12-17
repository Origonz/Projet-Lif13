package projet.modele;

import java.util.ArrayList;

/**
 *
 * @author billy
 */
public class Case {
    private int id;
    private int etatGraphique;
    
    //{^, >, v, <} (c'est les directions si t'as pas compris)
    private ArrayList<Integer> maze;
    
    Case(){
        id=0;
        etatGraphique = 0;
        maze = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            maze.add(0);
        }
    }

    public void setEtatGraphique(int etatGraphique) {
        this.etatGraphique = etatGraphique;
    }

    public int getEtatGraphique() {
        return etatGraphique;
    }
    
    Case(int i){
        id=i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Entre val dans le laby de la case (?)
     * @param direction 0=^ / 1=> / 2=v / 3=<
     * @param val 1=ouvert / 0=bloqué
     */
    public void setMazeValue(int direction, int val) {
        maze.set(direction, val);
    }
    
    public int getMazeValue(int direction) {
        return maze.get(direction);
    }
    
    public void refreshGraphique() {
        if (maze.get(0) == 1) {
            if (maze.get(1) == 1) etatGraphique = 5;
            else if (maze.get(3) == 1) etatGraphique = 3;
            else etatGraphique = 2;
        } else if (maze.get(1) == 1) {
            if (maze.get(2) == 1) etatGraphique = 6;
            else etatGraphique = 1;
        } else if (maze.get(2) == 1) {
            if (maze.get(3) == 1) etatGraphique = 4;
            else etatGraphique = 2;
        } else if (maze.get(3) == 1) {
            etatGraphique = 1;
        }
    }
}