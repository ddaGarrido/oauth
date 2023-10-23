package com.mygateway.mygatewayoauth.models;

import com.fasterxml.jackson.core.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import netscape.javascript.JSObject;
import org.bson.json.JsonObject;
import org.bson.json.JsonWriter;

@AllArgsConstructor
@Getter
public class JWTObject {
    private String subject;
    private String clientId;
    private String clientSecret;
    private String token;

}
