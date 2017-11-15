/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import projet.lif13.Grille;

/**
 *
 * @author Zonthem
 */
public class Game extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Grille gr = new Grille(3, 3);
        Case c;
        
        BorderPane border = new BorderPane();
        GridPane gPane = new GridPane();
        
        for (int i = 0; i < gr.getLongueur(); i++) {
            for (int j = 0; j < gr.getLargeur(); j++) {
                c = new Case((gr.getTab(i, j)), i, j);
                gPane.add(c, i, j);
                
                c.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ((Case)event.getSource()).changeEtat();
                    }
                });
            }
        }
        
        border.setCenter(gPane);
        
        Button bouton = new Button("Reset");
        bouton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Reset gr
            }
        });
        
        Scene scene = new Scene(border);
        
        primaryStage.setTitle("Le Jeu");
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
