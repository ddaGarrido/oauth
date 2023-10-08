package com.mygateway.mygatewayoauth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(String subject) {
        return Jwts.builder()
            .setSubject(subject)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }
}
