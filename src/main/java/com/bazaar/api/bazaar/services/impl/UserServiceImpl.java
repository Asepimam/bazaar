package com.bazaar.api.bazaar.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bazaar.api.bazaar.dtos.UserRegistrationRequest;
import com.bazaar.api.bazaar.dtos.UserResponse;
import com.bazaar.api.bazaar.dtos.UserUpdateRequest;
import com.bazaar.api.bazaar.entities.User;
import com.bazaar.api.bazaar.exceptions.ResourceFoundException;
import com.bazaar.api.bazaar.exceptions.ResourceNotFoundException;
import com.bazaar.api.bazaar.mapps.UserMapper;
import com.bazaar.api.bazaar.repositories.UserRepository;
import com.bazaar.api.bazaar.services.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with id: ";
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(
        rollbackFor = {ResourceFoundException.class}
    )
    public UserResponse createUser(UserRegistrationRequest request) {
        User user = userMapper.toEntity(request);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceFoundException("User already exists with email: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        log.info("User created - ID: {}, Email: {}", user.getId(), user.getEmail());
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE + id));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(
        rollbackFor = {ResourceNotFoundException.class}
    )
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE + id));
        
        userMapper.updateEntityFromRequest(request, user);
        
        userRepository.save(user);
        log.info("User updated with ID: {}", user.getId());

        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE + id));
        userRepository.delete(user);
        log.info("User deleted with ID: {}", user.getId());
    }
}