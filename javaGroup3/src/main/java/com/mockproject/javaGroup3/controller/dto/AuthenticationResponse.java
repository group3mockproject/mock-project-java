package com.mockproject.javaGroup3.controller.dto;

import com.mockproject.javaGroup3.model.User;

public record AuthenticationResponse(String token,User user) {
}
