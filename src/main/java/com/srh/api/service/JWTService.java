package com.srh.api.service;

import com.srh.api.model.UserApi;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${srh.jwt.expiration}")
    String tokenExpirationTime;

    @Value("${srh.jwt.secret}")
    String secret;

    public String buildToken(Authentication authentication) {
        UserApi userLogged = (UserApi) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(tokenExpirationTime));
        return buildJWT(userLogged, today, expirationDate);
    }

    public boolean isValidJWT(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }

    private String buildJWT(UserApi user, Date today, Date expirationDate) {
        return Jwts.builder()
                .setIssuer("Hybrid Recommendation System - SRH")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
