package com.example.authapi.dto.register;

import com.example.authapi.entity.user.UserRole;

public record RegisterRequestDTO (String name, String email, String password, UserRole role) {
}
