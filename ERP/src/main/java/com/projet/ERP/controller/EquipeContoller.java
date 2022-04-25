package com.projet.ERP.controller;

import com.projet.ERP.domain.Equipe;
import com.projet.ERP.repository.EquipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class EquipeContoller {

    @Autowired
    private EquipeRepository equipeRepository;
    
    @GetMapping("/equipe/")
    public @ResponseBody ResponseEntity<Iterable<Equipe>> getCollaborateur(){
        Iterable<Equipe> equipe = equipeRepository.findAll();
        return ResponseEntity.ok().body(equipe);
    }
}
