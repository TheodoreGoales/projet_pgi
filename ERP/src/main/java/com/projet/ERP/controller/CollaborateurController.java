package com.projet.ERP.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.projet.ERP.domain.Collaborateur;
import com.projet.ERP.domain.Equipe;
import com.projet.ERP.dto.CollaborateurRequest;
import com.projet.ERP.repository.CollaborateurRepository;
import com.projet.ERP.repository.EquipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class CollaborateurController {

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EquipeRepository equipeRepository;

    @GetMapping("/collaborateur/")
    public @ResponseBody ResponseEntity<Iterable<Collaborateur>> getCollaborateurs(){
        Iterable<Collaborateur> collabResult = collaborateurRepository.findByEquipe_IdEquipe(1);
        List<Collaborateur> collab = new ArrayList<Collaborateur>();
        int c=0;
        for(Collaborateur i : collabResult){
            if(i.getEquipe().getChef_equipe() == i.getId()){
                c=i.getId();
                collab.add(i);
                break;
            }
        }
        for(Collaborateur i : collabResult){
            if(i.getId()!=c){
                collab.add(i);
            }
        }
        return ResponseEntity.ok().body(collab);
    }

    @GetMapping("/collaborateur/{id}")
    public @ResponseBody ResponseEntity<Collaborateur> getCollaborateur(@PathVariable int id){
        Optional<Collaborateur> collabResult = collaborateurRepository.findById(id);
        if(collabResult.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Collaborateur collaborateur = collabResult.get();
        return ResponseEntity.ok().body(collaborateur);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/collaborateur/")
    public @ResponseBody ResponseEntity<Collaborateur> postCollaborateur(@RequestBody CollaborateurRequest collaborateurRequest){
        Optional<Equipe> result = equipeRepository.findById(collaborateurRequest.getEquipe_id_equipe());
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Collaborateur collab = new Collaborateur();
        collab.setNom(collaborateurRequest.getNom());
        collab.setPrenom(collaborateurRequest.getPrenom());
        collab.setMail(collaborateurRequest.getMail());
        collab.setTel(collaborateurRequest.getTel());
        collab.setSecteur(collaborateurRequest.getSecteur());
        collab.setEquipe(result.get());
        collab.setPassword(passwordEncoder.encode(collaborateurRequest.getPassword()));
        collaborateurRepository.save(collab);
        return ResponseEntity.ok().body(collab);
    }

    @DeleteMapping("/collaborateur/{id}")
    public @ResponseBody ResponseEntity<Collaborateur> deleteCollaborateur(@PathVariable int id){
        Optional<Collaborateur> collabResult = collaborateurRepository.findById(id);
        if(collabResult.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Collaborateur collaborateur = collabResult.get();
        collaborateurRepository.deleteById(id);
        return ResponseEntity.ok().body(collaborateur);
    }

    @PutMapping("/collaborateur/{id}")
    public @ResponseBody ResponseEntity<Collaborateur> replaceCollaborateur(@RequestBody CollaborateurRequest collaborateurRequest, @PathVariable int id) {
        Optional<Collaborateur> resultat = collaborateurRepository.findById(id);
        Optional<Equipe> resultat2 = equipeRepository.findById(collaborateurRequest.getEquipe_id_equipe());

        if(resultat.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(resultat2.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Collaborateur collab = resultat.get();
        collab.setNom(collaborateurRequest.getNom());
        collab.setPrenom(collaborateurRequest.getPrenom());
        collab.setMail(collaborateurRequest.getMail());
        collab.setTel(collaborateurRequest.getTel());
        collab.setSecteur(collaborateurRequest.getSecteur());
        collab.setPassword(passwordEncoder.encode(collaborateurRequest.getPassword()));
        collab.setEquipe(resultat2.get());
        collaborateurRepository.save(collab);
        return ResponseEntity.ok().body(collab);
    }  
}

