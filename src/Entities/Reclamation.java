/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author daami
 */
public class Reclamation {

    private int id_parent ;
    private String objet,type,contenu ; 
   
    private Date date ;

    public Reclamation() {
    }

    public Reclamation( int id_parent, String objet, String type, String contenu, Date date) {
       
        this.id_parent = id_parent;
        this.objet = objet;
        this.type = type;
        this.contenu = contenu;
        this.date = date;
    }

    public Reclamation(String objet, String type, String contenu, Date date) {
        this.objet = objet;
        this.type = type;
        this.contenu = contenu;
        this.date = date;
    }

  

    

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    



   
    
}    

