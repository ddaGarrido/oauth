package com.mygateway.mygatewayoauth.controllers;

import com.mygateway.mygatewayoauth.dto.RegisterDTO;
import com.mygateway.mygatewayoauth.models.User;
import com.mygateway.mygatewayoauth.repositories.UserRepository;
import com.mygateway.mygatewayoauth.services.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth/token")
public class OAuthController {

//    @Autowired
//    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    private UserRepository repository = new UserRepository();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid RegisterDTO data) {
        User user = repository.findByLogin(data.login());
        if(user == null) return ResponseEntity.badRequest().build();

//        var usernameAndPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var authentication = authenticationManager.authenticate(usernameAndPassword);

        //String token = jwtService.generateToken(user.getLogin());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        if(repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        //String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), data.password(), data.role());

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
