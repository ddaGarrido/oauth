package com.mygateway.mygatewayoauth.dto;

import com.mygateway.mygatewayoauth.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
