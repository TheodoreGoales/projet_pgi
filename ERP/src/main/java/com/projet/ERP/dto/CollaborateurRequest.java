package com.projet.ERP.dto;

public class CollaborateurRequest {
    private String nom; 
    private String prenom;
    private String mail;
    private String tel;
    private String secteur;
    private String password;
    private int equipe_id_equipe;

    public int getEquipe_id_equipe() {
        return this.equipe_id_equipe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEquipe_id_equipe(int equipe_id_equipe) {
        this.equipe_id_equipe = equipe_id_equipe;
    }

    public String getNom() {
        return this.nom;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }    
}
