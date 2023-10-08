package com.mygateway.mygatewayoauth.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mygateway.mygatewayoauth.config.DBConfig;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public abstract class GenRepository<T> {

    protected MongoCollection<T> collection;
    protected final MongoDatabase database = DBConfig.getDatabase();

    @PostConstruct
    public T findById(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }

    @PostConstruct
    public T findById(String fieldName, int id) {
        return collection.find(Filters.eq(fieldName, id)).first();
    }

    @PostConstruct
    public List<T> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    @PostConstruct
    public void save(T t) {
        collection.insertOne(t);
    }
}
