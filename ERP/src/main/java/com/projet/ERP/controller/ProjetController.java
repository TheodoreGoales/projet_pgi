package com.projet.ERP.controller;

import java.util.Optional;

import com.projet.ERP.domain.Projet;
import com.projet.ERP.repository.ProjetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

public class ProjetController {

    @Autowired
    private ProjetRepository projetRepository;
    
    @GetMapping("/projet/{id}")
    public @ResponseBody ResponseEntity<Projet> getProjet(@PathVariable int id){
        Optional<Projet> projetResult = projetRepository.findById(id);
        if(projetResult.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Projet projet = projetResult.get();
        return ResponseEntity.ok().body(projet);
    }

}
