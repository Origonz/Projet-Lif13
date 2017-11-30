

package projet.vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Zonthem
 */
public class FinalStage {
    
    private Label top, bottom;
    
    private Stage finalStage;

    public FinalStage(Stage mainStage) {
        bottom = new Label("Maintenant t'as plus qu'à reboot...");
        top = new Label();
        top.setFont(Font.font(42));
        
        finalStage = new Stage();
        
        GridPane gPane = new GridPane();
        
        gPane.add(top, 0, 0);
        gPane.add(bottom, 0, 1);
        gPane.setHgap(20);
        
        finalStage.initOwner(mainStage);
        finalStage.initModality(Modality.APPLICATION_MODAL);
        finalStage.setResizable(false);
        finalStage.getIcons().add(new Image("projet/ressources/logo.png"));
        finalStage.setTitle("Fin du game !");
        finalStage.setScene(new Scene(gPane));
    }
    
    public void setMessage(String msg) {
        top.setText(msg);
    }
    
    public void show() {
        finalStage.show();
    }
}
