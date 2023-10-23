package com.mygateway.mygatewayoauth.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBConfig {

    @Autowired
    private Properties props;

    public MongoClient connectMongoDB() {
        ConnectionString connectionString = new ConnectionString(props.getMonboDBUrl());

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        MongoClientSettings clientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();

        return MongoClients.create(clientSettings);
    }

    public MongoDatabase getDatabase() {
        MongoClient mongoClient = connectMongoDB();
        return mongoClient.getDatabase(props.getMongoDBName());
    }
}
