package com.example.authapi.dto.login;

import com.example.authapi.entity.user.UserRole;

public record LoginResponseDTO(
        String email,
        String token
) {
}
