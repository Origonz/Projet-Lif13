package projet.modele;

/**
 *
 * @author billy
 */
public class Case {
    private int id;
    private int etatGraphique;
    
    Case(){
        id=0;
        etatGraphique = 0;
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
}