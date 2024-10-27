package com.mockproject.javaGroup3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.javaGroup3.controller.dto.AuthenticationRequest;
import com.mockproject.javaGroup3.controller.dto.AuthenticationResponse;
import com.mockproject.javaGroup3.controller.dto.RegisterRequest;
import com.mockproject.javaGroup3.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public record AuthController(AuthenticationService authenticationService) {


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
    	
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
