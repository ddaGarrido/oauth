package com.mygateway.mygatewayoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MyGatewayOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGatewayOauthApplication.class, args);
	}

}
