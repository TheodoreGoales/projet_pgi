package com.projet.ERP.service;

import java.util.Optional;

import com.projet.ERP.domain.Collaborateur;
import com.projet.ERP.repository.CollaborateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        
        Optional<Collaborateur> result = collaborateurRepository.findByMail(mail);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
        return result.get();
    }
    
}
