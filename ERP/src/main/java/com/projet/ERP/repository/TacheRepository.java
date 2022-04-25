package com.projet.ERP.repository;

import java.util.List;
import java.util.Optional;

import com.projet.ERP.domain.Collaborateur;
import com.projet.ERP.domain.Tache;
import org.springframework.data.repository.CrudRepository;

public interface TacheRepository extends CrudRepository<Tache, Integer>{
    Optional<Iterable<Tache>> findByProjet_Id(int id);
    Optional<Iterable<Tache>> findDistinctByCollaborateurs_Id(int id);
}
