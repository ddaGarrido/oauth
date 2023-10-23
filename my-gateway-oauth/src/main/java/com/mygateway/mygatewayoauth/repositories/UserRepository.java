package com.mygateway.mygatewayoauth.repositories;

import com.mygateway.mygatewayoauth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    List<User> findByClientId(String clientId);
    List<User> findByClientSecret(String clientSecret);
    User findByClientIdAndClientSecret(String clientId, String clientSecret);
}
