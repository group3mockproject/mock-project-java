package com.mockproject.javaGroup3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.javaGroup3.model.User;
import com.mockproject.javaGroup3.util.JwtService;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController { // Thay record bằng class

    @GetMapping
    public String sayHello(Authentication authentication) {
        return """
                Hello %s 🥳 !
                Welcome to a very secured page  😱
                """.formatted(getName(authentication));
    }

    

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')") // Cả USER và ADMIN đều có thể truy cập
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("Welcome User");
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Welcome Admin");
    }
    @GetMapping("/admin1")
    public ResponseEntity<String> adminEndpoint(@RequestHeader("Authorization") String token) {
        List<String> roles = JwtService.extractRoles(token.substring(7)); // Bỏ "Bearer " ra khỏi token

        if (roles.contains("ADMIN")) {
            return ResponseEntity.ok("Welcome Admin");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
    }

    @GetMapping("/user1")
    public ResponseEntity<String> userEndpoint(@RequestHeader("Authorization") String token) {
        List<String> roles = JwtService.extractRoles(token.substring(7));
        if (roles.contains("USER") || roles.contains("ADMIN")) {
            return ResponseEntity.ok("Welcome User");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
    }
    private String getName(Authentication authentication) {
        return Optional.of(authentication)
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .map(User::getEmail)
                .orElseGet(authentication::getName);
    }
}
