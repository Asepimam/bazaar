package com.bazaar.api.bazaar.services;

import com.bazaar.api.bazaar.dtos.AuthResponse;
import com.bazaar.api.bazaar.dtos.LoginRequest;

public interface AuthService {

    public AuthResponse login(LoginRequest request);
}
