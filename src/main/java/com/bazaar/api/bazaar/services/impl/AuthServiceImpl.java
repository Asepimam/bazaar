package com.bazaar.api.bazaar.services.impl;

import java.util.List;
import java.util.Map;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bazaar.api.bazaar.dtos.AuthResponse;
import com.bazaar.api.bazaar.dtos.LoginRequest;
import com.bazaar.api.bazaar.exceptions.InvalidCrendecialException;
import com.bazaar.api.bazaar.repositories.UserRepository;
import com.bazaar.api.bazaar.services.AuthService;
import com.bazaar.api.bazaar.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            var userOpt = userRepository.findByEmail(request.getEmail());
            if(userOpt.isPresent() && passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
                List<String> token = jwtUtil.generateToken(userOpt.get().getUsername(), 
                    Map.of("roles", userOpt.get().getRole(), "email", userOpt.get().getEmail())
                );
                return AuthResponse.builder()
                        .accessToken(token.get(0))
                        .refreshToken(token.get(1))
                        .build();
            } else {
                throw new InvalidCrendecialException("Invalid email or password");
            }
        } catch (Exception e) {
            log.error("Login failed for email: {}", request.getEmail(), e);
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

}