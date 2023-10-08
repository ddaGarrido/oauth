package com.mygateway.mygatewayoauth.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
public abstract class DBModel {
    @MongoId
    protected ObjectId _id;

    protected long createdAt;
    protected long updatedAt;
}
