package com.mygateway.mygatewayoauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:env.properties")
public class Properties {

    @Autowired
    private Environment env;

    public String getSecretKey() {
        return env.getProperty("security.jwt.secret-key");
    }

    public String getAccessTokenDuration() {
        return env.getProperty("security.jwt.access-token-duration");
    }

    public String getRefreshTokenDuration() {
        return env.getProperty("security.jwt.refresh-token-duration");
    }

    public String getMongoDBName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    public String getMonboDBUrl() {
        return env.getProperty("spring.data.mongodb.uri");
    }
}
