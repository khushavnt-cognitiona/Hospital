package com.yourshop.services;

import com.yourshop.dto.auth.AuthResponse;
import com.yourshop.dto.auth.LoginRequest;
import com.yourshop.dto.auth.RegisterRequest;
import com.yourshop.dto.user.UserResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getCurrentUser();
}