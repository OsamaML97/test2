/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Reclamation;
import Entities.User;
import java.io.IOException;
import java.net.URL;
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
public class GestionUserController implements Initializable {

    @FXML
    private TableView< User> tvv;
    @FXML
    private TableColumn<String, User> cn;
    @FXML
    private TableColumn<String, User> cun;
    @FXML
    private TableColumn<String, User> ca;
    private ObservableList<User>data;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private Button Back;
    DataBaseConnection conn;
    ResultSet rs;
    PreparedStatement pst;
    @FXML
    private TextField tfn;
    @FXML
    private TextField tfun;
    @FXML
    private TextField tfad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn=DataBaseConnection.getInstance();
        try {
            affichage();
        } catch (SQLException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Add(ActionEvent event) {
        
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
                conn=DataBaseConnection.getInstance();
           int selectedIndex = tvv.getSelectionModel().getSelectedIndex();
        User c = (User) tvv.getSelectionModel().getSelectedItem();
        int tag = c.getId();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getId()+ " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                String sql = "DELETE FROM user WHERE id = ?";
                pst = conn.getCnx().prepareStatement(sql);
                pst.setInt(1, tag);
                pst.execute();
                tvv.getItems().remove(selectedIndex);

            }

        }
    }
      public void affichage() throws SQLException{
        conn=DataBaseConnection.getInstance();
    
     try {
            
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM user";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                //data.addAll(data);
                data.add(new User (rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       cn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cun.setCellValueFactory(new PropertyValueFactory<>("username"));
        ca.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    
 
        tvv.setItems(null);
        tvv.setItems(data);
          
   
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
        conn=DataBaseConnection.getInstance();
           String sql = "update user set name=?,username=?,adresse=? where id=?";

            pst = conn.getCnx().prepareStatement(sql);

            pst.setString(1, tfn.getText());
            pst.setString(2, tfun.getText());
            pst.setString(3, tfad.getText());
            

            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Mise à jour a été effectué avec succès");
            alert.showAndWait();

            pst.close();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage =new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root =loader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
