package com.projet.ERP.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projet.ERP.domain.Collaborateur;
import com.projet.ERP.dto.TokenRequest;
import com.projet.ERP.dto.TokenResponse;
import com.projet.ERP.repository.CollaborateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class SecurityController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private CollaborateurRepository collaborateurRepository;

	@PostMapping("/authorize")
	public @ResponseBody ResponseEntity<?> authorize(@RequestBody TokenRequest requestDto) {
        // Authenticating user from username and password
		Authentication authentication = new UsernamePasswordAuthenticationToken(requestDto.getMail(), requestDto.getPassword());
		try {
			System.out.println("test1");
			authentication = authenticationManager.authenticate(authentication);
			System.out.println(authentication);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
        // Generating a token (Json Web Token)
		Collaborateur user = (Collaborateur) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600 * 1000 * 10); //dernier paramètre, le nombre d'heure de validité
        String token =  Jwts.builder().setSubject(user.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, "GestionJeu")
            .compact();

        // Outputing results into a response dto
		TokenResponse responseDto = new TokenResponse();
		responseDto.setAccessToken(token);
		List<String> authoritiesDto = new ArrayList<String>();
		user.getAuthorities().forEach(x -> {
			authoritiesDto.add(x.getAuthority());
		});
		responseDto.setAuthorities(authoritiesDto);
        responseDto.setIssuedAt(now);
        responseDto.setExpiresAt(expiryDate);
		
		return ResponseEntity.ok(responseDto);
	}

    @GetMapping("/me")
    public @ResponseBody ResponseEntity<Collaborateur> me() {
        try {
            Collaborateur user = (Collaborateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok().body(user);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public @ResponseBody ResponseEntity<Collaborateur> register(@RequestBody TokenRequest requestDto) {
		Collaborateur collab = new Collaborateur();
		collab.setMail(requestDto.getMail());
		collab.setPassword(passwordEncoder.encode(requestDto.getPassword()));
		collaborateurRepository.save(collab);
		return ResponseEntity.ok(collab);
	}
}
