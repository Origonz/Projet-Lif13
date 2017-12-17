/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projet.controleur.Controleur;
import projet.modele.Grille;

/**
 *
 * @author Zonthem
 */
public class FenGame extends Application{
    
    private Option opt;
    
    private FinalStage fin;
    
    private GridPane gPane;
    
    private Controleur cont;
    
    private Scene scene;
    
    private Stage primStage;
    
    @Override
    public void start(Stage primaryStage) {
        primStage = primaryStage;
        
        cont = new Controleur(this);
        opt = new Option(primaryStage, cont);
        fin = new FinalStage(primaryStage);
        
        initGrid();
        
        primStage.setTitle("Connects");
        primStage.setResizable(false);
        primStage.getIcons().add(new Image("projet/ressources/logo.png"));
        primStage.show();
    }
    
    public void afficherFin(boolean estGangnant) {
        fin.setMessage(estGangnant ? "GG !" : "Perdu...");
        fin.show();
    }
    
    public void restart() {
        for (Node n : gPane.getChildren()) {
            if (n instanceof Case)
                ((Case)n).chargeDefaut();
        }
    }
    
    public void reset() {
        initGrid();
    }
    
    private void initGrid() {
        Case c;
        Separator s;
        
        cont.placementpoints();
        Grille gr = cont.getGrille();
        BorderPane mainBorder = new BorderPane();
        gPane = new GridPane();
                
        for (int i = 0; i < gr.getLongueur(); i++) {
            for (int j = 0; j < gr.getLargeur(); j++) {
                c = new Case((gr.getTab(i, j)), i, j, Integer.max(cont.getLargeur(), cont.getLongueur()));
                gPane.add(c, i, j);
                
                c.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ((Case)event.getSource()).changeEtat(cont);
                    }
                });
            }
        }
        
        s = new Separator(Orientation.HORIZONTAL);
        s.setScaleX(100);
        gPane.add(s, 0, gr.getLargeur());
        
        mainBorder.setCenter(gPane);
        
        Button bouton = new Button("Recommencer");
        bouton.setPrefWidth(100);
        bouton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cont.restart();
            }
        });
        
        Button b2 = new Button("Options");
        b2.setPrefWidth(100);
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
        BorderPane.setMargin(boutonPane, new Insets(10));
        gPane.setVgap(50 / Integer.max(cont.getLargeur(), cont.getLongueur()));
        gPane.setHgap(50 / Integer.max(cont.getLargeur(), cont.getLongueur()));
        boutonPane.setVgap(20);
        boutonPane.setHgap(20);
        
        scene = new Scene(mainBorder);
        
        primStage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}