package com.example.authapi.dto.register;

import com.example.authapi.entity.user.UserRole;

public record RegisterResponseDTO(
        String name,
        String email,
        UserRole role
) {
}
