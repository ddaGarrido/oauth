package com.mygateway.mygatewayoauth.controllers;

import com.mygateway.mygatewayoauth.models.User;
import com.mygateway.mygatewayoauth.repositories.UserRepository;
import com.mygateway.mygatewayoauth.services.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(HttpServletRequest request,
                                       @RequestParam String grant_type,
                                       @RequestParam String scope) {

        // todo externalizar isso
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            return ResponseEntity.status(401).body("Unauthorized - missing or invalid Authorization header");
        }
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);
        String clientId = values[0];
        String clientSecret = values[1];

        // todo Validar clientId e clientSecret
        User user = repository.findByClientIdAndClientSecret(clientId, clientSecret);
        if (user == null ){//|| !user.isActive()) {
            return ResponseEntity.status(401).body("Unauthorized - invalid client credentials");
        }

        // todo Validar grant_type, scope, user roles etc
//        if (!"password".equals(grant_type) || !"read".equals(scope)) {
//            return ResponseEntity.badRequest().body("Invalid grant_type or scope");
//        }

        // Gerar token de acesso e todo gerar regresh token
        String accessToken = jwtService.generateToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user); // Implemente este método conforme necessário

        // todo externalizar Criar resposta
        OAuthResponse response = new OAuthResponse();
        response.setAccessToken(accessToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(3600); // Expiração em segundos
        response.setRefreshToken(null);
        response.setScope(scope);

        return ResponseEntity.ok(response);
    }

    // Classe para representar a resposta OAuth
    @Setter
    @Getter
    public static class OAuthResponse {
        private String accessToken;
        private String tokenType;
        private int expiresIn;
        private String refreshToken;
        private String scope;

        // getters e setters
    }
}
