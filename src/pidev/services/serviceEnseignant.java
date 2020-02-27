/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import DataBaseConnection.DataBaseConnection;
import Entities.Enseignant;
import Entities.Enseignant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ghass
 */
public class serviceEnseignant {
    
    Connection c = DataBaseConnection.getInstance().getCnx();
        Statement st;
        public void ajouterEnseignant(Enseignant e) throws SQLException
        {
            //try {

                st = c.createStatement();
                String req = "INSERT INTO Enseignant VALUES(null,"+ e.getCIN() +",'"+e.getEvaluation() +"','"+ e.getNom() + "','" + e.getPrenom() + "',"+ e.getAbsence() + "," + e.getSalaire() + ")";

                st.executeUpdate(req);
          //  } catch (SQLException ex) {
              //  Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
          //  }
        }

        public void modifierEnseignant(int id, String nom, String prenom, int CIN, float salaire, int absence, String evaluation){

            try {

                PreparedStatement pst = c.prepareStatement("update Enseignant set nom=?, prenom=?, CIN=?, salaire=?,absence=?, evaluation=? where ID_enseignant=?");
                pst.setString(1,nom);
                pst.setString(2,prenom);
                pst.setInt(3,CIN);
                pst.setFloat(4,salaire);
                pst.setInt(5,absence);
                pst.setString(6,evaluation);
                pst.setInt(7, id);
                pst.execute();
            } catch (SQLException ex) {

                Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void afficherEnseignant(){
            PreparedStatement pt;
            try {
                pt = c.prepareStatement("SELECT * FROM Enseignant");

                ResultSet rs = pt.executeQuery();

                while (rs.next()){
                    System.out.printf("ID Ensegnant : %d , Nom : %s , Prenom : %s , CIN : %d , Salaire : %s , Absence : %s , Evaluation : %s%n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6), rs.getString(7));
                }
            } catch (SQLException ex) {
                Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void supprimerEnseignant( int id){
            PreparedStatement pt;
            try {
                pt = c.prepareStatement("DELETE FROM Enseignant WHERE ID_Enseignant =?");
                pt.setInt(1,id);
                pt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void rechercherEnseignant(int ID_Enseignant){

            PreparedStatement ps ;
            try {
                ps= c.prepareStatement("SELECT * FROM Enseignant WHERE ID_Enseignant =?");
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    System.out.println("L'enseignant sous ID Enseignant : "+rs.getInt(1)+
                            " , Nom : "+rs.getString(2)+
                            " , Prenom : "+rs.getString(3)+
                            " , CIN : "+rs.getInt(4)+
                            " , Salaire : "+rs.getFloat(5)+
                            " , Absence : "+rs.getString(6)+
                            " , Evaluation : "+rs.getString(7));
                }
            } catch (SQLException ex) {
                Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public void triEnseignantParID_ASC(){
            PreparedStatement ps ;
            try {
                ps= c.prepareStatement("SELECT * FROM Enseignat ORDER BY ID_Enseignant ASC ");
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    System.out.println("ID Ensegnant : "+rs.getInt(1)+
                            " , Nom : "+rs.getString(2)+
                            " , Prenom : "+rs.getString(3)+
                            " , CIN : "+rs.getInt(4)+
                            " , Salaire : "+rs.getFloat(5)+
                            " , Absence : "+rs.getString(6)+
                            " , Evaluation : "+rs.getString(7));
                }
            } catch (SQLException ex) {
                Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void triEnseignantParID_DESC(){
            PreparedStatement ps ;
            try {
                ps= c.prepareStatement("SELECT * FROM Enseignat ORDER BY ID_Enseignant DESC ");
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    System.out.println("ID Enseignant : "+rs.getInt(1)+
                            " , Nom : "+rs.getString(2)+
                            " , Prenom : "+rs.getString(3)+
                            " , CIN : "+rs.getInt(1)+
                            " , Salaire : "+rs.getFloat(5)+
                            " , Absence : "+rs.getString(6)+
                            " , Evaluation : "+rs.getString(7));
                }
            } catch (SQLException ex) {
                Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public ObservableList <Enseignant> readAll () throws SQLException {
            ObservableList<Enseignant> arr = FXCollections.observableArrayList();
            Statement ste;
            ste = c.createStatement();
            ResultSet rs = ste.executeQuery("select * from Enseignant");
            while (rs.next()){
                int id  = rs.getInt(1);
                String Nom = rs.getString(4);
                String Prenom = rs.getString(5);
                int CIN = rs.getInt(2);
                float Salaire = rs.getFloat(7);
                int absence = rs.getInt(6);
                String Evaluation = rs.getString(3);
                Enseignant e = new Enseignant(id,Nom,Prenom,Evaluation,CIN,absence,Salaire);
                arr.add(e);
            }
            return arr;
        }

    
}
