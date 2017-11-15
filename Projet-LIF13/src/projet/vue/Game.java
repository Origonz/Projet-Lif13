/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import projet.lif13.Grille;
import projet.lif13.Case;

/**
 *
 * @author Zonthem
 */
public class Game extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Grille gr = new Grille(6, 3);
        Text t;
        
        BorderPane border = new BorderPane();
        GridPane gPane = new GridPane();
        
        for (int i = 0; i < gr.getLongueur(); i++) {
            for (int j = 0; j < gr.getLargeur(); j++) {
                t = new Text(Integer.toString(gr.getTab(i, j)));
                t.setWrappingWidth(30);
                t.setTextAlignment(TextAlignment.CENTER);
                gPane.add(t, i, j);
            }
        }
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(gPane, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
