/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;
import Entities.Complaints;
import pidev.Iservices.IServise;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataBaseConnection.DataBaseConnection;
import Entities.Complaints;
/**
 *
 * @author daami
 */
public class ServiceComplaint implements pidev.Iservices.IServise<Complaints>{

    private DataBaseConnection conn;
    private Statement ste;
    
    public ServiceComplaint() throws SQLException {
      conn =DataBaseConnection.getInstance();
    

    }
    
    @Override
    public void ajouter(Complaints t) throws SQLException {
        PreparedStatement pre=conn.getCnx().prepareStatement("INSERT INTO `jardins`.`reclamation` ( `etat`, `description`, `image` , `note` , `type` , `user_id`) VALUES ( ?, ?, ?, ? ,? ,?);");
        pre.setString(1, t.getEtat());
        pre.setString(2, t.getDescription());
        pre.setString(3, t.getImage());
        pre.setInt(4, t.getNote());
        pre.setString(5, t.getType());
        pre.setInt(6, t.getUser_id());
        pre.executeUpdate();    
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(chercher(id)){
        PreparedStatement pre=conn.getCnx().prepareStatement("DELETE FROM `jardins`.`reclamation`  WHERE id_reclamamtion = (?) ;");
        pre.setInt(1,id);
        if (pre.executeUpdate() != 0)
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Complaints t, int id) throws SQLException {
        PreparedStatement pre=conn.getCnx().prepareStatement("UPDATE `jardins`.`reclamation` SET `etat` = ? WHERE `id_reclamamtion` = ? ;");
        pre.setString(1, t.getEtat());
   //    pre.setString(2, t.getDescription());
//      pre.setString(3, t.getImage());
//        pre.setInt(4, t.getNote());
//        pre.setString(5, t.getType());
        pre.setInt(2,id);
        if (pre.executeUpdate() != 0)
            return true;
        return false;
        
    }

    @Override
    public List<Complaints> readAll() throws SQLException {
        List<Complaints> arr=new ArrayList<>();
    ste=conn.getCnx().createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String etat=rs.getString(2);
               String description=rs.getString(3);
               String image=rs.getString(4);
               int note=rs.getInt(5);
               String type=rs.getString(6);
               int user_id=rs.getInt(7);
               
               Complaints p=new Complaints(id, etat, description, image, note, type, user_id);
     arr.add(p);
     }
    return arr;
    }
    
    
   /* public List<Complaints> recherche_userID(int user_id) throws SQLException{
            List<Complaints> arr=new ArrayList<>();
            PreparedStatement pre=con.prepareStatement("SELECT * FROM `jardins`.`reclamation` WHERE `user_id` = ? ;");
        pre.setInt(1,user_id);
        
        ResultSet rs=pre.executeQuery();
        while (rs.next()) {                
               int id=rs.getInt(1);
               String etat=rs.getString(2);
               String description=rs.getString(3);
               String image=rs.getString(4);
               int note=rs.getInt(5);
               String type=rs.getString(6);
              
               
               Complaints p=new Complaints(id, etat, description, image, note, type);
     arr.add(p);
     }
    return arr;
//               Complaints p=new Complaints(id, etat, description, image, note, type);
//     arr.add(p);
//     }
   
    }
    */
    
  
    public boolean chercher(int id) throws SQLException {
        String req="select * from reclamation  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=conn.getCnx().createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceComplaint.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
    
    
}
