/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projet.controleur.Controleur;

/**
 *
 * @author Zonthem
 */
public class Option {
    
    private final Stage optionStage;
    
    private int longueur, largeur;
    
    private TextField txtLongueur, txtLargeur;
    
    private Controleur cont;
    
    public Option(Stage mainStage, Controleur c) {
        optionStage = new Stage();
        cont = c;
        //récupérer longueur et largeur depuis la grille
        longueur = c.getLongueur();
        largeur = c.getLargeur();
        BorderPane optBorder = new BorderPane();
        GridPane optGPane = new GridPane();
        
        txtLongueur = new TextField(Integer.toString(longueur));
        txtLargeur = new TextField(Integer.toString(largeur));
        Label lblLongueur = new Label("Longueur du plateau : ");
        Label lblLargeur = new Label("Largeur du plateau : ");
        Button bAccept = new Button("Valider");
        bAccept.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Valider();
            }
        });
        Button bAnnule = new Button("Annuler");
        bAnnule.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                close();
            }
        });
        
        optGPane.add(lblLongueur, 0, 0);
        optGPane.add(txtLongueur, 1, 0);
        optGPane.add(lblLargeur, 0, 1);
        optGPane.add(txtLargeur, 1, 1);
        optGPane.add(bAccept, 0, 2);
        optGPane.add(bAnnule, 1, 2);
        optBorder.setPadding(new Insets(10));
        
        optBorder.setCenter(optGPane);
        Scene scene = new Scene(optBorder);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Valider();
                    event.consume();
                }
            }
            
        });
        
        optionStage.initOwner(mainStage);
        optionStage.initModality(Modality.APPLICATION_MODAL);
        optionStage.setResizable(false);
        optionStage.getIcons().add(new Image("projet/ressources/logo.png"));
        optionStage.setTitle("Options de jeu");
        optionStage.setScene(scene);
    }
    
    public void Show() {
        txtLongueur.setText(Integer.toString(longueur));
        txtLargeur.setText(Integer.toString(largeur));
        optionStage.show();
    }
    
    public void close() {
        optionStage.hide();
    }
    
    public void Valider() {
        if (cont.acceptSetDimensions(txtLongueur.getText(), txtLargeur.getText())) {
            longueur = Integer.parseInt(txtLongueur.getText());
            largeur = Integer.parseInt(txtLargeur.getText());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information");
            a.setHeaderText(null);
            a.setContentText("Le jeu va automatiquement redémarrer pour appliquer les modifications");
            a.showAndWait();
            cont.reset();
            close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention !");
            a.setHeaderText(null);
            a.setContentText("Les valeurs entrées ne sont pas des nombres...");
            a.showAndWait();
        }
    }
}
