/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Events;
import pidev.services.serviceEvent;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class AfficherEventController implements Initializable {
    @FXML
    private TableColumn <Events,String> coloneTitre;
    @FXML
    private TableColumn <Events,String> coloneProgramme;
    @FXML
    private TableColumn <Events,String> coloneRegion;
    @FXML
    private TableColumn <Events,Integer> coloneIDEvent;
    @FXML
    private TableColumn <Events, String> coloneDebut;
    @FXML
    private TableColumn <Events,String> coloneFin;
    @FXML
    private TableColumn <Events,String> coloneModifier;
    @FXML
    private TableColumn <Events,String> coloneSupprimer;
    @FXML
    private TableColumn <Events,Integer> coloneNbr;
    @FXML
    private TableView <Events> tableEvent;
    @FXML
    private TextField txnbrPar;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField txRegion;
    @FXML
    private TextField txTitre;
    @FXML
    private TextArea txProgramme;
    @FXML
    private TextField txtdbj;
    @FXML
    private TextField txtdda;
    @FXML
    private TextField txtddm;
    @FXML
    private TextField txtdfj;
    @FXML
    private TextField txtdfm;
    @FXML
    private TextField txtdfa;
    @FXML
    private Label lbdatedebut;
    @FXML
    private Label lbdatefin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            serviceEvent se = new serviceEvent();
            ObservableList<Events> list = FXCollections.observableArrayList();
            
            list = se.readAll();
            
            txTitre.setVisible(false);
            txProgramme.setVisible(false);
            txRegion.setVisible(false);
            txnbrPar.setVisible(false);
            btnValider.setVisible(false);
            
            txtdbj.setVisible(false);
            txtddm.setVisible(false);
            txtdda.setVisible(false);
            txtdfj.setVisible(false);
            txtdfm.setVisible(false);
            txtdfa.setVisible(false);
            
            lbdatefin.setVisible(false);
            lbdatedebut.setVisible(false);
            btnAnnuler.setVisible(false);
            coloneIDEvent.setCellValueFactory(new PropertyValueFactory<>("ID_Event"));
            coloneTitre.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
            coloneProgramme.setCellValueFactory(new PropertyValueFactory<>("Programme"));
            coloneRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
            coloneNbr.setCellValueFactory(new PropertyValueFactory<>("nbrParticipant"));
            coloneDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            coloneFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            
            Callback<TableColumn<Events,String>, TableCell<Events,String>> cellFactory = param -> {
                final TableCell<Events, String> cell = new TableCell<Events,String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btnEdit = new Button("Modifier");
                            btnEdit.setOnAction(event -> {
                                txTitre.setVisible(true);
                                txProgramme.setVisible(true);
                                txRegion.setVisible(true);
                                txnbrPar.setVisible(true);
                                btnValider.setVisible(true);
                                txtdbj.setVisible(true);
                                txtddm.setVisible(true);
                                txtdda.setVisible(true);
                                txtdfj.setVisible(true);
                                txtdfm.setVisible(true);
                                txtdfa.setVisible(true);
                                lbdatedebut.setVisible(true);
                                lbdatefin.setVisible(true);
                                btnAnnuler.setVisible(true);
                                setCellValue();
                                
                            });
                            
                            setGraphic(btnEdit);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            
            Callback<TableColumn<Events,String>, TableCell<Events,String>> cellFactory2 = param -> {
                final TableCell<Events,String> cell = new TableCell<Events,String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btnDelete = new Button ("Supprimer");
                            btnDelete.setOnAction(event -> {
                                Events e2 = getTableView().getItems().get(getIndex());
                                se.supprimerEvent(e2.getID_Event());
                                ObservableList<Events> listUpdate = FXCollections.observableArrayList();
                                try {
                                    listUpdate = se.readAll();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                tableEvent.setItems(listUpdate);
                            });
                            setGraphic(btnDelete);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            coloneModifier.setCellFactory(cellFactory);
            coloneSupprimer.setCellFactory(cellFactory2);
            tableEvent.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void modifierEvent (MouseEvent event) {
        serviceEvent se  = new serviceEvent();
        String datedebut =txtdda.getText()+"-"+txtddm.getText()+"-"+txtdbj.getText();
        String datefin =txtdfa.getText()+"-"+txtdfm.getText()+"-"+txtdfj.getText();
        Events e = tableEvent.getItems().get(tableEvent.getSelectionModel().getSelectedIndex());
        se.modifierEvent(e.getID_Event(),txTitre.getText(),txProgramme.getText(),txRegion.getText(),datedebut,datefin,Integer.parseInt(txnbrPar.getText()));
        ObservableList<Events> listUpdate = FXCollections.observableArrayList();
       
        try {
            listUpdate = se.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableEvent.setItems(listUpdate);
        txTitre.setText("");
        txProgramme.setText("");
        txRegion.setText("");
        txnbrPar.setText("");
        txtdbj.setText("");
        txtddm.setText("");
        txtdda.setText("");
        txtdfj.setText("");
        txtdfm.setText("");
        txtdfa.setText("");
        
        lbdatedebut.setVisible(false);
        lbdatefin.setVisible(false);
        txTitre.setVisible(false);
        txProgramme.setVisible(false);
        txRegion.setVisible(false);
        txnbrPar.setVisible(false);
        btnValider.setVisible(false);
        btnAnnuler.setVisible(false);
        txtdbj.setVisible(false);
        txtddm.setVisible(false);
        txtdda.setVisible(false);
        txtdfj.setVisible(false);
        txtdfm.setVisible(false);
        txtdfa.setVisible(false);
    }
    public void setCellValue(){
        tableEvent.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Events e = tableEvent.getItems().get(tableEvent.getSelectionModel().getSelectedIndex());
                String datedebut =txtdda.getText()+"-"+txtddm.getText()+"-"+txtdbj.getText();
                String datefin =txtdfa.getText()+"-"+txtdfm.getText()+"-"+txtdfj.getText();
            txTitre.setText(e.getNomEvent());
            txProgramme.setText(e.getProgramme());
            txRegion.setText(e.getRegion());
            txnbrPar.setText(String.valueOf(e.getNbrParticipant()));
            
            String anneed = e.getDateDebut().substring(0,4);
            String moisd = e.getDateDebut().substring(5,7);
            String jourd = e.getDateDebut().substring(8,10);
            txtdda.setText(anneed);
            txtddm.setText(moisd);
            txtdbj.setText(jourd);
            
            String anneef = e.getDateFin().substring(0,4);
            String moisf = e.getDateFin().substring(5,7);
            String jourf = e.getDateFin().substring(8,10);
            txtdfa.setText(anneef);
            txtdfm.setText(moisf);
            txtdfj.setText(jourf);
            

            //datedebut.setText(e.getDateDebut());
           //datefin.setText(e.getDateFin());
            }
        });
    }
}
