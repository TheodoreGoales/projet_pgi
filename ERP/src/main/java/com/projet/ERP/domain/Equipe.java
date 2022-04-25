package com.projet.ERP.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Equipe {
    @Id
    @GeneratedValue
    private int idEquipe;
    private int chef_equipe;

    public Equipe() {
    }

    public Equipe(int idEquipe, int chef_equipe) {
        this.idEquipe = idEquipe;
        this.chef_equipe = chef_equipe;
    }

    public int getId() {
        return this.idEquipe;
    }

    public void setId(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getChef_equipe() {
        return this.chef_equipe;
    }

    public void setChef_equipe(int chef_equipe) {
        this.chef_equipe = chef_equipe;
    }
}
