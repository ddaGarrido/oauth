package com.mygateway.mygatewayoauth.services;

import com.mygateway.mygatewayoauth.models.User;
import com.mygateway.mygatewayoauth.config.Properties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JWTService {

    @Autowired
    private Properties props;

    public String generateToken(User user) {
        return generateToken(user.getUsername(), user.getClientId(), user.getClientSecret());
    }

    public String generateToken(String clientName, String clientId, String clientSecret){
        return Jwts.builder()
                .setId(clientId)
                .claim("jts", clientSecret)
                .setSubject(clientName)
                .setExpiration(genExpirationDate())
                .signWith(SignatureAlgorithm.HS256, props.getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(props.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Date genExpirationDate(){
        return Date.from(LocalDateTime.now().plusSeconds(60).toInstant(ZoneOffset.of("-03:00")));
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(props.getSecretKey())
                    .parseClaimsJws(token);

            // Aqui, você pode adicionar mais validações, como verificar a expiração do token
            // ...

            return true;
        } catch (SignatureException e) {
            // Se a assinatura não for válida, isso será lançado
            return false;
        }
    }
}
