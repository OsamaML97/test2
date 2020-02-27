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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daami
 */
public class ClassesModuleController implements Initializable {

    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private JFXTextField name_tf;
    @FXML
    private JFXTextField cap_tf;
    @FXML
    private JFXTextField desc_tf;
    
    private DataBaseConnection conn;
    private DataBaseConnection dc;
    private PreparedStatement pst;
    @FXML
    private JFXTextField idC_tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn=DataBaseConnection.getInstance();
    }    

    @FXML
    private void add(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        conn=DataBaseConnection.getInstance();
        String sql ="insert into classes (nom,capacite,description) values (?,?,?)";
       // Integer.valueOf(idC_tf.getText());
        String name = name_tf.getText();
        Integer.valueOf(cap_tf.getText());
        String desc= desc_tf.getText();
        
        try {
            pst = conn.getCnx().prepareStatement(sql);
           
            pst.setString(1, name);
            pst.setInt(2, Integer.valueOf(cap_tf.getText()));
            pst.setString(3, desc);
       
            int i=pst.executeUpdate();
       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Classe ajoutée");
            alert.showAndWait();
            pst.close();
             } catch (SQLException ex) {
            Logger.getLogger(ClassesModuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
