package com.bazaar.api.bazaar.services;

import com.bazaar.api.bazaar.dtos.UserRegistrationRequest;
import com.bazaar.api.bazaar.dtos.UserResponse;
import com.bazaar.api.bazaar.dtos.UserUpdateRequest;

public interface UserService {
    public UserResponse createUser(UserRegistrationRequest request);
    public UserResponse getUserById(Long id);
    public void deleteUser(Long id);
    public UserResponse updateUser(Long id, UserUpdateRequest request);
}
