/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Classes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daami
 * 
 */
public class HomeController implements Initializable {
    private PreparedStatement pst;
    private ResultSet rs;
    private DataBaseConnection conn ;
    
    private ObservableList<Classes> data;
    @FXML
    private TableView<Classes> Table_Classes;
    @FXML
    private TableColumn<Integer, Classes> column_id;
    @FXML
    private TableColumn<String, Classes> colum_name;
    @FXML
    private TableColumn<Integer, Classes> colum_cap;
    @FXML
    private TableColumn<String, Classes> colum_Desc;
    @FXML
    private JFXButton btn_ajouter;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXButton btn_supp;
    @FXML
    private ImageView imageview;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField Recherche_Tf;
    @FXML
    private Button gestion1;
    @FXML
    private Button gestion2;
    Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            affichage();
             conn =DataBaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    


    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_ajouter.getScene().getWindow();
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/ClassesModule.fxml"));
        Parent root = (Parent) Loader.load();
        //scene = new Scene(root);
        
        Stage stage1 = new Stage();
        stage1.setTitle("Ajouter une classe");
        stage1.setScene(new Scene(root));
        stage1.show();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_ajouter.getScene().getWindow();
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/UpdateClasse.fxml"));
        Parent root = (Parent) Loader.load();
        //scene = new Scene(root);
        
        Stage stage1 = new Stage();
        stage1.setTitle("Modifier une classe");
        stage1.setScene(new Scene(root));
        stage1.show();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
                conn = DataBaseConnection.getInstance();
        int selectedIndex = Table_Classes.getSelectionModel().getSelectedIndex();
        Classes c = (Classes) Table_Classes.getSelectionModel().getSelectedItem();
        int tag = c.getIdC();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getName()+ " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                String sql = "DELETE FROM classes WHERE idC = ?";
                pst = conn.getCnx().prepareStatement(sql);
                pst.setInt(1, tag);
                pst.execute();
                Table_Classes.getItems().remove(selectedIndex);

            }

        }
    }
    
    
    
        public void affichage() throws SQLException{
        conn=DataBaseConnection.getInstance();
    
     try {
            
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM classes";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                //data.addAll(data);
                data.add(new Classes(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       column_id.setCellValueFactory(new PropertyValueFactory<>("idC"));
        colum_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        colum_cap.setCellValueFactory(new PropertyValueFactory<>("cap"));
        colum_Desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
 
        Table_Classes.setItems(null);
        Table_Classes.setItems(data);
          
   
    }
    @FXML
    private void Rechercher(ActionEvent event) {
        
                 FilteredList<Classes> filteredData=new FilteredList<>(data, e-> true);
                 Recherche_Tf.setOnKeyReleased(e-> {
                 Recherche_Tf.textProperty().addListener((observableValue, oldValue, newValue)-> {
                 filteredData.setPredicate((Predicate<? super Classes>) (c) -> {
                 if(newValue == null || newValue.isEmpty()){
                 return true;
                 
                 } 
                 String lowercasefilter = newValue.toLowerCase();
                 if(c.getName().toLowerCase().contains(lowercasefilter)){
                 return true;
                 }  
                 else if(c.getDesc().toLowerCase().contains(lowercasefilter)){
                 return true;
                 }
                 return false;
                 
                 });
             
             });
                SortedList<Classes> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(Table_Classes.comparatorProperty());
                Table_Classes.setItems(sortedData);
                     });
    }

    @FXML
    private void gestus(ActionEvent event) throws IOException {
        stage = (Stage)gestion1.getScene().getWindow();
               root =FXMLLoader.load(getClass().getResource("/FXML/GestionUser.fxml"));
              Scene scene =new Scene(root);
                stage.setScene(scene);
               
              stage.show();
        
        
    }

    @FXML
    private void gestrec(ActionEvent event) throws IOException {
        stage = (Stage)gestion2.getScene().getWindow();
               root =FXMLLoader.load(getClass().getResource("/FXML/Gestionreclamation.fxml"));
              Scene scene =new Scene(root);
                stage.setScene(scene);
               
              stage.show();
        
        
    }
}
