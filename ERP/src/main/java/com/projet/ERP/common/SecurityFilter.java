package com.projet.ERP.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.ERP.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityService securityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            String token = extractTokenFromRequest(request);
            String mail = extractUsernameFromToken(token);
            setPrincipal(mail);
        } catch(Exception e) {
            logger.info("Authentification par token en erreur : " + e.getMessage());
        }
        
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }
        return authorization.substring(7, authorization.length());
    }

    private String extractUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("GestionJeu").parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private void setPrincipal(String mail) {
        UserDetails user = securityService.loadUserByUsername(mail);
        UsernamePasswordAuthenticationToken springToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(springToken);
    }
    
}
