package com.projet.ERP.dto;

import java.sql.Date;

public class TacheRequest {
    private String intitule;
    private int etat;
    private Date date_debut;
    private Date date_fin;
    private String notes;
    private int projet_id;    

    public String getIntitule() {
        return this.intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getEtat() {
        return this.etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Date getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getProjet_id() {
        return this.projet_id;
    }

    public void setProjet_id(int projet_id) {
        this.projet_id = projet_id;
    }
}
