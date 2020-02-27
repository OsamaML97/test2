/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Enseignant;
import pidev.services.serviceEnseignant;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class AfficherEnseignantControllers implements Initializable {

    @FXML
    private TableView<Enseignant> tableEnseignant;
    @FXML
    private TableColumn<Enseignant, Integer> coloneID;
    @FXML
    private TableColumn<Enseignant, String> coloneNom;
    @FXML
    private TableColumn<Enseignant, String> colonePrenom;
    @FXML
    private TableColumn<Enseignant, Integer> coloneCIN;
    @FXML
    private TableColumn<Enseignant, Float> coloneSalaire;
    @FXML
    private TableColumn<Enseignant, Integer> coloneAbsence;
    @FXML
    private TableColumn<Enseignant, String> coloneModifier;
    @FXML
    private TableColumn<Enseignant, String> coloneSupprimer;
    @FXML
    private Button btnValider;
    @FXML
    private TextField txNom;
    @FXML
    private TextField txCIN;
    @FXML
    private TextField txAbsence;
    @FXML
    private TextField txPrenom;
    @FXML
    private TextField txSalaire;
    @FXML
    private TextField txEvaluation;
    @FXML
    private TableColumn<Enseignant, String> coloneEvaluation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            serviceEnseignant se = new serviceEnseignant();
            ObservableList<Enseignant> list = FXCollections.observableArrayList();
            
            list = se.readAll();
            
            txNom.setVisible(false);
            txPrenom.setVisible(false);
            txCIN.setVisible(false);
            txEvaluation.setVisible(false);
            txAbsence.setVisible(false);
            txSalaire.setVisible(false);
            btnValider.setVisible(false);
            
            coloneID.setCellValueFactory(new PropertyValueFactory<>("IDenseignant"));
            coloneNom.setCellValueFactory(new PropertyValueFactory<>("Nom")  );
            colonePrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            coloneCIN.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            coloneSalaire.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
            coloneEvaluation.setCellValueFactory(new PropertyValueFactory<>("Evaluation"));
            coloneAbsence.setCellValueFactory(new PropertyValueFactory<>("Absence"));
            
            Callback<TableColumn<Enseignant, String>, TableCell<Enseignant, String>> cellFactory = (param) -> {
                final TableCell<Enseignant, String> cell = new TableCell<Enseignant, String>(){
                    @Override
                    public void updateItem (String item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btnEdit = new Button("Modifier");
                            btnEdit.setOnAction(event -> {
                                txNom.setVisible(true);
                                txPrenom.setVisible(true);
                                txCIN.setVisible(true);
                                txEvaluation.setVisible(true);
                                txAbsence.setVisible(true);
                                txSalaire.setVisible(true);
                                btnValider.setVisible(true);
                                setCellValue();
                            });
                            setGraphic(btnEdit);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            
            Callback<TableColumn<Enseignant,String>, TableCell<Enseignant,String>> cellFactory2 = param -> {
                final TableCell<Enseignant,String> cell = new TableCell<Enseignant, String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btnDelete = new Button("Supprimer");
                            btnDelete.setOnAction(event -> {
                                Enseignant e2 = getTableView().getItems().get(getIndex());
                                
                                se.supprimerEnseignant(e2.getID_enseignant());
                                ObservableList<Enseignant> listUpdate = FXCollections.observableArrayList();
                                try {
                                    listUpdate = se.readAll();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AfficherEnseignantControllers.class.getName()).log(Level.SEVERE, null ,ex);
                                }
                                tableEnseignant.setItems(listUpdate);
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
            tableEnseignant.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEnseignantControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierEvent (MouseEvent event){
        serviceEnseignant se = new serviceEnseignant();
        Enseignant e = tableEnseignant.getItems().get(tableEnseignant.getSelectionModel().getSelectedIndex());
        se.modifierEnseignant(e.getID_enseignant(),txNom.getText(),txPrenom.getText(),Integer.parseInt(txCIN.getText()),Float.parseFloat(txSalaire.getText()),Integer.parseInt(txAbsence.getText()),txEvaluation.getText());
        ObservableList<Enseignant> listUpdate = FXCollections.observableArrayList();
     
        try {
            listUpdate = se.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEnseignantControllers.class.getName()).log(Level.SEVERE, null ,ex);
        }
        
        tableEnseignant.setItems(listUpdate);
        txNom.setText("");
        txPrenom.setText("");
        txCIN.setText("");
        txEvaluation.setText("");
        txAbsence.setText("");
        txSalaire.setText("");
        
        txNom.setVisible(false);
        txPrenom.setVisible(false);
        txCIN.setVisible(false);
        txEvaluation.setVisible(false);
        txAbsence.setVisible(false);
        txSalaire.setVisible(false);
        btnValider.setVisible(false);

    }

    public void setCellValue(){
        tableEnseignant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Enseignant  e = tableEnseignant.getItems().get(tableEnseignant.getSelectionModel().getSelectedIndex());
                txNom.setText(e.getNom());
                txPrenom.setText(e.getPrenom());
                txCIN.setText(String.valueOf(e.getCIN()));
                txAbsence.setText(String.valueOf(e.getAbsence()));
                txSalaire.setText(String.valueOf(e.getSalaire()));
                txEvaluation.setText(e.getEvaluation());
            }
        });
    }
    
    
}
