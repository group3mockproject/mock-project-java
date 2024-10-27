package com.mockproject.javaGroup3.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockproject.javaGroup3.controller.dto.AuthenticationRequest;
import com.mockproject.javaGroup3.controller.dto.AuthenticationResponse;
import com.mockproject.javaGroup3.controller.dto.RegisterRequest;
import com.mockproject.javaGroup3.model.Role;
import com.mockproject.javaGroup3.model.User;
import com.mockproject.javaGroup3.respository.UserRepository;
import com.mockproject.javaGroup3.util.JwtService;



@Service
public record AuthenticationService(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager) {
    public AuthenticationResponse register(RegisterRequest request) {
    	Role userRole = Role.valueOf(request.role().toUpperCase());
        final var user = new User(null,
                request.firstname(),
                request.lastname(),
                request.email(),
                passwordEncoder.encode(request.password()),
                userRole);
        System.out.println("Role của user trước khi lưu: " + user.getRole());  // In ra để kiểm tra
        userRepository.save(user);
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse( token , user );
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final var user = userRepository.findByEmail(request.email()).orElseThrow();
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token,user);

    }
}
