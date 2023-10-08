package com.mygateway.mygatewayoauth.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Document(collection = "Users")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private ObjectId id;
    private String login;
    private String password;
    private String token;
    private UserRole role;

    public User() {
    }

    public User(@BsonProperty("login") String login,
                @BsonProperty("password") String password,
                @BsonProperty("role") UserRole role,
                @BsonProperty("token") String token) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}