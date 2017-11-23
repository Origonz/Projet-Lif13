/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projet.lif13.Grille;

/**
 *
 * @author Zonthem
 */
public class Game extends Application {
    
    Option opt;
    
    @Override
    public void start(Stage primaryStage) {
        Grille gr = new Grille(3, 3);
        Case c;
        opt = new Option(primaryStage);
        
        BorderPane mainBorder = new BorderPane();
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
        
        mainBorder.setCenter(gPane);
        
        //ici on s'occupe des options panes
        
        //ici c'est fini
        
        Button bouton = new Button("Reset");
        bouton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Reset gr
                for (Node n : gPane.getChildren()) {
                    ((Case)n).reset();
                }
            }
        });
        
        Button b2 = new Button("Options");
        b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                opt.Show();
            }
        });
        
        GridPane boutonPane = new GridPane();
        boutonPane.add(bouton, 0, 0);
        boutonPane.add(b2, 1, 0);
        
        mainBorder.setBottom(boutonPane);
        
        mainBorder.setPadding(new Insets(10));
        BorderPane.setMargin(boutonPane, new Insets(20, 10, 10, 10));
        gPane.setVgap(20);
        gPane.setHgap(20);
        boutonPane.setVgap(20);
        boutonPane.setHgap(20);
        
        Scene scene = new Scene(mainBorder);
        
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