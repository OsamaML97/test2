/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daami
 */
public class UpdateClasseController implements Initializable {

    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private JFXTextField name_tf;
    @FXML
    private JFXTextField cap_tf;
    @FXML
    private JFXTextField desc_tf;

 
    private PreparedStatement pst;
    private DataBaseConnection conn;
    @FXML
    private Label name_tf1;
    @FXML
    private JFXTextField idC_tf;
    @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn =DataBaseConnection.getInstance();
        
        
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException {
       conn = DataBaseConnection.getInstance();

            String sql = "update classes set nom=?,capacite=?,description=? where idC=?";

            pst = conn.getCnx().prepareStatement(sql);

            pst.setString(1, name_tf.getText());
            pst.setString(2, cap_tf.getText());
            pst.setString(3, desc_tf.getText());
            pst.setString(4, idC_tf.getText());

            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Mise à jour a été effectué avec succès");
            alert.showAndWait();

            pst.close();
 
            }

  

    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
             ((Node)event.getSource()).getScene().getWindow().hide();
            Stage s1=new Stage();
            FXMLLoader L=new FXMLLoader();
            Pane root=L.load(getClass().getResource("Home.fxml"));
            Scene c=new Scene(root);
            
            s1.setScene(c);
    }
    
}
