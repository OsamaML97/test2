/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Events;
import pidev.services.serviceEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class FormulaireEventController implements Initializable {

    @FXML
    private TextArea txProgramme;
    @FXML
    private TextField txTitre;
    @FXML
    private TextField txRegion;
    @FXML
    private TextField txnbrPar;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField txtddj;
    @FXML
    private TextField txtddm;
    @FXML
    private TextField txtdda;
    @FXML
    private TextField txtdfj;
    @FXML
    private TextField txtdfm;
    @FXML
    private TextField txtdfa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void envoyerEvent(MouseEvent event){
        serviceEvent se = new serviceEvent();
        String datedebut =txtdda.getText()+"-"+txtddm.getText()+"-"+txtddj.getText();
        String datefin =txtdfa.getText()+"-"+txtdfm.getText()+"-"+txtdfj.getText();
        Events e = new Events(txTitre.getText(),txProgramme.getText(),txRegion.getText(),datedebut,datefin,Integer.parseInt(txnbrPar.getText()));
        se.ajouterEvent(e);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Formulaire_event.fxml"));
        
    }
    
}
