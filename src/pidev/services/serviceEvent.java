/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import DataBaseConnection.DataBaseConnection;
import pidev.services.serviceEnseignant;
import Entities.Events;
import Entities.Events;
import Entities.Events;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ghass
 */
public class serviceEvent {
        Connection c = DataBaseConnection.getInstance().getCnx();

    Statement st;

    public void ajouterEvent(Events e)
    {
        try {
            st=c.createStatement();
            String req="INSERT INTO events VALUES(NULL,'" +e.getNomEvent()+"','"+e.getProgramme()+"','"+e.getRegion()+"','"+e.getDateDebut()+"','"+
                    e.getDateFin()+"',"+e.getNbrParticipant()+")";
                    
            /*
                String req="INSERT INTO `Events`(`nomEvent`, `Programme`, `region`, `dateDebut`, `dateFin`, `ID_Event`, `nbrParticipant`) " +
                   "VALUES ("+e.getNomEvent()+","+e.getProgramme()+","+e.getRegion()+","+e.getDateDebut()+","+e.getDateFin()+","+e.getID_Event()+","+e.getNbrParticipant()+")";
            */
            st.executeUpdate(req);
       } catch (SQLException ex) {
            Logger.getLogger(serviceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierEvent(int id, String nomEvent, String programme, String region, String dateDebut, String dateFIN, int nbrParticiapant){

        try {

            PreparedStatement pst = c.prepareStatement("update events set nomEvent=?, programme=?, region=?, dateDebut=?,dateFin=?, nbrParticipant=? where ID_Event=?");
            pst.setString(1,nomEvent);
            pst.setString(2,programme);
            pst.setString(3,region);
            pst.setString(4, dateDebut);
            pst.setString(5,dateFIN);
            pst.setInt(6,nbrParticiapant);
            pst.setInt(7, id);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(serviceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherEvent(){
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("SELECT * FROM Events");

            ResultSet rs = pt.executeQuery();

            while (rs.next()){
                System.out.println("ID Event : "+rs.getInt(1)+
                        " , Titre : "+rs.getString(2)+
                        " , Programme : "+rs.getString(3)+
                        " , region : "+rs.getString(4)+
                        " , Date Début : "+rs.getString(5)+
                        " , Date FIN : "+rs.getString(6)+
                        " , Nombre des Participant : "+rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerEvent(int id){

        try {
            PreparedStatement pt = c.prepareStatement("DELETE FROM Events WHERE ID_Event =?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(serviceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rechercherEvent(int ID_Event){

        PreparedStatement ps ;
        try {
            ps= c.prepareStatement("SELECT * FROM Events WHERE ID_Event =?");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("ID Event : "+rs.getInt(1)+
                        " , Titre : "+rs.getString(2)+
                        " , Programme : "+rs.getString(3)+
                        " , region : "+rs.getString(4)+
                        " , Date Début : "+rs.getString(5)+
                        " , Date FIN : "+rs.getString(6)+
                        " , Nombre des Participant : "+rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void triEventParID_ASC(){
        PreparedStatement ps ;
        try {
            ps= c.prepareStatement("SELECT * FROM Events ORDER BY ID_Events ASC ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("ID Event : "+rs.getInt(1)+
                        " , Titre : "+rs.getString(2)+
                        " , Programme : "+rs.getString(3)+
                        " , region : "+rs.getString(4)+
                        " , Date Début : "+rs.getString(5)+
                        " , Date FIN : "+rs.getString(6)+
                        " , Nombre des Participant : "+rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void triEventParID_DESC(){
        PreparedStatement ps ;
        try {
            ps= c.prepareStatement("SELECT * FROM Events ORDER BY ID_Event DESC ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("ID Event : "+rs.getInt(1)+
                        " , Titre : "+rs.getString(2)+
                        " , Programme : "+rs.getString(3)+
                        " , region : "+rs.getString(4)+
                        " , Date Début : "+rs.getString(5)+
                        " , Date FIN : "+rs.getString(6)+
                        " , Nombre des Participant : "+rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Events> readAll() throws SQLException {
        ObservableList<Events> arr = FXCollections.observableArrayList();
        Statement ste;
        ste = c.createStatement();
        ResultSet rs = ste.executeQuery("select * from Events");
        while (rs.next()){
            int id = rs.getInt(1);
            String titre= rs.getString(2);
            String programme = rs.getString(3);
            String region = rs.getString(4);
            String dateDebut = rs.getString(5);
            String dateFin = rs.getString(6);
            int nbr = rs.getInt(7);
            
            
            Events e = new Events(id,titre,programme,region,dateDebut,dateFin,nbr);
            arr.add(e);
        }
        return arr;
    }

    
}
