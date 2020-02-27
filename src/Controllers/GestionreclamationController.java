/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Classes;
import Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daami
 */
public class GestionreclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tabv;
    @FXML
    private TableColumn<String, Reclamation> ct;
    @FXML
    private TableColumn<String, Reclamation> co;
    @FXML
    private TableColumn<String, Reclamation> cc;
    @FXML
    private TableColumn<Date, Reclamation> cdd;
    private ObservableList<Reclamation>data;
    @FXML
    private TextField rft;
    @FXML
    private TextField tfo;
    @FXML
    private TextField tfc;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private DatePicker datep;
    private DataBaseConnection conn;
    ResultSet rs;
    PreparedStatement pst;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            conn=DataBaseConnection.getInstance();
            affichage();
        } catch (SQLException ex) {
            Logger.getLogger(GestionreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        conn=DataBaseConnection.getInstance();
           String sql="insert into reclamation (type,object,contenu,date) values (?,?,?,?)";
        try {
            pst=conn.getCnx().prepareStatement(sql);
            pst.setString(1, rft.getText());
            pst.setString(2, tfo.getText());
            pst.setString(3,tfc.getText());
            
            pst.setDate(4, java.sql.Date.valueOf(datep.getValue()));
            int i =pst.executeUpdate();
           
                Alert al =new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("reclamation ajoutée");
                al.showAndWait();
            
                
        } catch (SQLException ex) {
            Logger.getLogger(GestionreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void affichage() throws SQLException{
        conn=DataBaseConnection.getInstance();
    
     try {
            
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM reclamation";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                //data.addAll(data);
                data.add(new Reclamation(rs.getString(2), rs.getString(3), rs.getString(4),rs.getDate(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       ct.setCellValueFactory(new PropertyValueFactory<>("type"));
        co.setCellValueFactory(new PropertyValueFactory<>("object"));
        cc.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        cdd.setCellValueFactory(new PropertyValueFactory<>("date"));
 
        tabv.setItems(null);
        tabv.setItems(data);
          
   
    }
    @FXML
    private void supp(ActionEvent event) throws SQLException {
        conn=DataBaseConnection.getInstance();
           int selectedIndex = tabv.getSelectionModel().getSelectedIndex();
        Reclamation c = (Reclamation) tabv.getSelectionModel().getSelectedItem();
        int tag = c.getId_parent();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getId_parent()+ " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                String sql = "DELETE FROM reclamation WHERE id_parent = ?";
                pst = conn.getCnx().prepareStatement(sql);
                pst.setInt(1, tag);
                pst.execute();
                tabv.getItems().remove(selectedIndex);

            }

        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        conn=DataBaseConnection.getInstance();
           String sql = "update reclamation set type=?,object=?,contenu=? where id_parent=?";

            pst = conn.getCnx().prepareStatement(sql);

            pst.setString(1, rft.getText());
            pst.setString(2, tfo.getText());
            pst.setString(3, tfc.getText());
            

            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Mise à jour a été effectué avec succès");
            alert.showAndWait();

            pst.close();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage =new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root =loader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
