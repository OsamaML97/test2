/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ghass
 */
public class Enseignant {
    
    private String nom;
    private String prenom;
    private String evaluation;
    private int ID_enseignant;
    private int CIN;
    private int absence;
    private float salaire;

    public Enseignant(String nom, String prenom, String evaluation, int CIN, int absence, float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.evaluation = evaluation;
        this.CIN = CIN;
        this.absence = absence;
        this.salaire = salaire;
    }
    public Enseignant(int ID_enseignant,String nom, String prenom, String evaluation, int CIN, int absence, float salaire) {
        this.ID_enseignant = ID_enseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.evaluation = evaluation;
        this.CIN = CIN;
        this.absence = absence;
        this.salaire = salaire;
    }
        //Getters & Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getID_enseignant() {
        return ID_enseignant;
    }

    public void setID_enseignant(int ID_enseignant) {
        this.ID_enseignant = ID_enseignant;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
    // ToString()

    @Override
    public String toString() {
        return "Enseignant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", ID_enseignant=" + ID_enseignant +
                ", CIN=" + CIN +
                ", absence=" + absence +
                ", salaire=" + salaire +
                '}';
    }
}
