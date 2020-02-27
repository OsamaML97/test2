/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ghass
 */
public class Events {
    
    private String nomEvent;
    private String Programme;
    private String region;
    private String dateDebut;
    private String dateFin;
    private int ID_Event;
    private int nbrParticipant;
    //private String Liste etudiant;


    public Events(String nomEvent, String programme, String region, String dateDebut, String dateFin, int nbrParticipant) {
        this.nomEvent = nomEvent;
        Programme = programme;
        this.region = region;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrParticipant = nbrParticipant;
    }

    public Events(int ID_Event,String nomEvent, String Programme, String region, String dateDebut, String dateFin, int nbrParticipant) {
        this.nomEvent = nomEvent;
        this.Programme = Programme;
        this.region = region;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ID_Event = ID_Event;
        this.nbrParticipant = nbrParticipant;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public String getProgramme() {
        return Programme;
    }

    public String getRegion() {
        return region;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public int getID_Event() {
        return ID_Event;
    }

    public int getNbrParticipant() {
        return nbrParticipant;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public void setProgramme(String programme) {
        Programme = programme;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setID_Event(int ID_Event) {
        this.ID_Event = ID_Event;
    }

    public void setNbrParticipant(int nbrParticipant) {
        this.nbrParticipant = nbrParticipant;
    }

    @Override
    public String toString() {
        return "Events{" +
                "nomEvent='" + nomEvent + '\'' +
                ", Programme='" + Programme + '\'' +
                ", region='" + region + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", ID_Event=" + ID_Event +
                ", nbrParticipant=" + nbrParticipant +
                '}';
    }
    
}
