package com.projet.ERP.repository;

import java.util.Optional;

import com.projet.ERP.domain.Collaborateur;

import org.springframework.data.repository.CrudRepository;

public interface CollaborateurRepository extends CrudRepository<Collaborateur, Integer>{
    Iterable<Collaborateur> findByEquipe_IdEquipe(int idEquipe);
    Optional<Collaborateur> findByMail(String mail);
}
