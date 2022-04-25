package com.projet.ERP.domain;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Tache {
    @Id
    @GeneratedValue
    private int id;
    private String intitule;
    private int etat; 
    private Date date_debut;
    private Date date_fin;
    private String notes;

    @ManyToOne
    private Projet projet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tache_collaborateur",
               joinColumns = @JoinColumn(name = "fk_id_tache"),
               inverseJoinColumns = @JoinColumn(name = "fk_id_collaborateur")
    )
    private Collection<Collaborateur> collaborateurs;

    public Tache(){
        
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Projet getProjet() {
        return this.projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
