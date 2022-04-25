package com.projet.ERP.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.projet.ERP.domain.Collaborateur;
import com.projet.ERP.domain.Projet;
import com.projet.ERP.domain.Tache;
import com.projet.ERP.dto.TacheRequest;
import com.projet.ERP.repository.ProjetRepository;
import com.projet.ERP.repository.TacheRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TacheController {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired ProjetRepository projetRepository;

    @GetMapping("/tache/")
    public @ResponseBody ResponseEntity<Iterable<Tache>> getTaches(){
        Optional<Iterable<Tache>> tacheResult = tacheRepository.findByProjet_Id(1);
        Iterable<Tache> taches = tacheResult.get();
        return ResponseEntity.ok().body(taches);
    }

    @GetMapping("/tache/collaborateur/{id}")
    public @ResponseBody ResponseEntity<Iterable<Tache>> getTachesCollab(){
        ArrayList<Collaborateur> collab = new ArrayList<>();
        Optional<Iterable<Tache>> tacheResult = tacheRepository.findDistinctByCollaborateurs_Id(1);
        Iterable<Tache> taches = tacheResult.get();
        return ResponseEntity.ok().body(taches);
    }

    @GetMapping("/tache/{id}")
    public @ResponseBody ResponseEntity<Tache> getTache(@PathVariable int id){
        Optional<Tache> tacheResult = tacheRepository.findById(id);
        if(tacheResult.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Tache tache = tacheResult.get();
        return ResponseEntity.ok().body(tache);
    }

    @PutMapping("/tache/{id}")
    public @ResponseBody ResponseEntity<Tache> editTache(@RequestBody TacheRequest tacheRequest, @PathVariable int id){
        Optional<Tache> resultat = tacheRepository.findById(id);
        Optional<Projet> resultat2 = projetRepository.findById(tacheRequest.getProjet_id());

        if(resultat.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(resultat2.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Tache tache = resultat.get();
        tache.setIntitule(tacheRequest.getIntitule());
        tache.setEtat(tacheRequest.getEtat());
        tache.setDate_debut(tacheRequest.getDate_debut());
        tache.setDate_fin(tacheRequest.getDate_fin());
        tache.setNotes(tacheRequest.getNotes());
        tache.setProjet(resultat2.get());
        return ResponseEntity.ok().body(tache);
    }
}