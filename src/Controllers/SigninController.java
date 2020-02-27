/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import Entities.User;
import java.sql.ResultSet;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;
import pidev.Iservices.NewClass;


/**
 * FXML Controller class
 *
 * @author daami
 */
public class SigninController implements Initializable {
    
    @FXML
    private VBox rightBox;
    
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField nameField;
    
    
    @FXML
    private JFXPasswordField passwordField;
    
    JFXSnackbar errorMsg;
    
     
     private Connection con;
     private DataBaseConnection conn;
     PreparedStatement pst;
    @FXML
    private Button btnsignUp;
    @FXML
    private Button btnLogin;
    Stage stage ;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField tfad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMsg = new JFXSnackbar(rightBox);
        conn=DataBaseConnection.getInstance();
       // con = dc.getConnection();
    }
    


      
      
    
    private void btnExit() {
        Platform.exit();
    }

    @FXML
    private void signup(ActionEvent event) throws MessagingException {
        conn=DataBaseConnection.getInstance();
        String sql ="insert into user (username,password,name,adresse) values (?,?,?,?)";
       // Integer.valueOf(idC_tf.getText());
        String name = nameField.getText();
        String pass=passwordField.getText();
        String us=usernameField.getText();
        
        try {
            pst = conn.getCnx().prepareStatement(sql);
           
            pst.setString(1, name);
            pst.setString(2, pass);
            pst.setString(3, us);
            pst.setString(4, tfad.getText());
       
            int i=pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Classe ajoutée");
            alert.showAndWait();
          String aa=  mail.getText();
            NewClass nc= new NewClass(mail.getText());
            
            pst.close();
             } catch (SQLException ex) {
            Logger.getLogger(ClassesModuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
              conn=DataBaseConnection.getInstance();
      String sql ="select * from user where username =? and password =?";
     try{
      pst=conn.getCnx().prepareStatement(sql);
      pst.setString(1, usernameField.getText());
      pst.setString(2,passwordField.getText() );
      
      ResultSet rs= pst.executeQuery();
      if(rs != null )
      {
          if(!rs.next())
          {
          Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setContentText("Username or mot de passe is incorrect");
                  alert.showAndWait();
                      
          } else{
                 System.out.println(rs.getInt(1));
              System.out.println(rs.getString(2));
              System.out.println(rs.getString(3));
              System.out.println(rs.getString(4));
              stage = (Stage)btnLogin.getScene().getWindow();
               root =FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
              Scene scene =new Scene(root);
                stage.setScene(scene);
               
              stage.show();
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("Connected");
              alert.showAndWait();
          }  
      
      
      
      
      
      
      } }catch(SQLException ex){
                  System.out.println(ex.getMessage());
                  
                  }
     
                         
      }
      
      
      

    }
