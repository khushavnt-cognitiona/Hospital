package com.yourshop.services;

import com.yourshop.dto.auth.AuthResponse;
import com.yourshop.dto.auth.LoginRequest;
import com.yourshop.dto.auth.RegisterRequest;
import com.yourshop.entities.Role;
import com.yourshop.entities.User;
import com.yourshop.mappers.UserMapper;
import com.yourshop.repositories.RoleRepository;
import com.yourshop.repositories.UserRepository;
import com.yourshop.security.JwtUtil;
import com.yourshop.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserMapper userMapper;
    private AuthService authService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtUtil = mock(JwtUtil.class);
        authenticationManager = mock(AuthenticationManager.class);
        userMapper = mock(UserMapper.class);
        authService = new AuthServiceImpl(userRepository, roleRepository, passwordEncoder, jwtUtil, authenticationManager, userMapper);
    }

    @Test
    void register_success() {
        RegisterRequest req = new RegisterRequest();
        req.setName("John");
        req.setEmail("john@example.com");
        req.setPassword("secret");
        when(userRepository.existsByEmail(req.getEmail())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(Role.builder().id(1L).name("ROLE_USER").build()));
        when(passwordEncoder.encode(anyString())).thenReturn("hashed");
        when(jwtUtil.generateToken(anyString(), anyMap())).thenReturn("token");
        AuthResponse res = authService.register(req);
        assertEquals("token", res.getToken());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void login_success() {
        LoginRequest req = new LoginRequest();
        req.setEmail("john@example.com");
        req.setPassword("secret");
        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        com.yourshop.security.CustomUserDetails cud = Mockito.mock(com.yourshop.security.CustomUserDetails.class);
        when(auth.getPrincipal()).thenReturn(cud);
        when(cud.getUsername()).thenReturn("john@example.com");
        when(cud.getAuthorities()).thenReturn(Set.of(() -> "ROLE_USER"));
        when(jwtUtil.generateToken(anyString(), anyMap())).thenReturn("token");
        AuthResponse res = authService.login(req);
        assertEquals("token", res.getToken());
    }
}