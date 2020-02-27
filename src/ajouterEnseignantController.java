/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Enseignant;
import pidev.services.serviceEnseignant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class ajouterEnseignantController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txNom;
    @FXML
    private TextField txPrenom;
    @FXML
    private TextField txCIN;
    @FXML
    private TextField txSalaire;
    @FXML
    private TextField txEvaluation;
    @FXML
    private TextField txAbsence;
    @FXML
    private Button btnEnvoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerEnseignant(MouseEvent event) {
        serviceEnseignant se  = new serviceEnseignant();
        Enseignant e = new Enseignant(txNom.getText(),txPrenom.getText(),txEvaluation.getText(),Integer.parseInt(txCIN.getText()),Integer.parseInt(txAbsence.getText()),Float.parseFloat(txSalaire.getText()));
        
        try {
            se.ajouterEnseignant(e);
        } catch (SQLException ex) {
            Logger.getLogger(ajouterEnseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Formulaire_enseignant.fxml"));
        
            Parent root;
        try {
            root = loader.load();
            ajouterEnseignantController c = loader.getController();
            btnEnvoyer.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ajouterEnseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
    }
    }
    

