package com.yourshop.dto.user;

import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Set<String> roles;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}