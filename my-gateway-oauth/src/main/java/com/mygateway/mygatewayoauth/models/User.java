package com.mygateway.mygatewayoauth.models;

import jakarta.persistence.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Document(collection = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private ObjectId id;
    private String username;
    private String clientId;
    private String clientSecret;
    private String status;
    private UserRole[] roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}