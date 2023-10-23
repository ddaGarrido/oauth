package com.mygateway.mygatewayoauth.controllers;

import com.mygateway.mygatewayoauth.models.User;
import com.mygateway.mygatewayoauth.models.UserRole;
import com.mygateway.mygatewayoauth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        User user = new User();
        user.setUsername(username);
        user.setClientId(UUID.randomUUID().toString());
        user.setClientSecret(UUID.randomUUID().toString());
        user.setStatus("INACTIVE");
        user.setRoles(new UserRole[]{UserRole.USER});

        return ResponseEntity.ok(repository.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestParam String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setUsername(username);
        return ResponseEntity.ok(repository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(user);
        return ResponseEntity.ok().build();
    }
}
