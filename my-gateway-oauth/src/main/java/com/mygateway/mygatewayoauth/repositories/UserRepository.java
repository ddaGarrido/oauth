package com.mygateway.mygatewayoauth.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mygateway.mygatewayoauth.config.DBConfig;
import com.mygateway.mygatewayoauth.models.User;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    protected MongoCollection<User> collection;
    protected final MongoDatabase database = DBConfig.getDatabase();

    public UserRepository() {
        collection = database.getCollection("Users", User.class);
    }

    public User findById(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }

    public User findByLogin(String login) {
        return collection.find(Filters.eq("login", login)).first();
    }

    public void save(User user) {
        collection.insertOne(user);
    }
}
