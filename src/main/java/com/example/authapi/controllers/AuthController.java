package com.example.authapi.controllers;

import com.example.authapi.constants.api.AuthenticationConstants;
import com.example.authapi.constants.user.UserConstants;
import com.example.authapi.dto.default_responses.DefaultResponseDTO;
import com.example.authapi.dto.login.LoginResponseDTO;
import com.example.authapi.dto.register.RegisterResponseDTO;
import com.example.authapi.entity.user.User;
import com.example.authapi.dto.login.LoginRequestDTO;
import com.example.authapi.dto.register.RegisterRequestDTO;
import com.example.authapi.entity.user.UserRole;
import com.example.authapi.security.TokenService;
import com.example.authapi.repositories.UserRepository;
import com.example.authapi.security.anotations.HasAdminRole;
import com.example.authapi.security.anotations.HasOrganizerRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity<DefaultResponseDTO> login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException(UserConstants.NOT_FOUND + body.email()));

        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(new DefaultResponseDTO(null, new LoginResponseDTO(user.getName(), token)));
        }
        return ResponseEntity.badRequest().body(new DefaultResponseDTO(AuthenticationConstants.WRONG_EMAIL_OR_PASSWORD, null));
    }


    @PostMapping("/signup")
    public ResponseEntity<DefaultResponseDTO> register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = createUserModel(body.name(), body.email(), body.password(), body.role());
            this.repository.save(newUser);
            return ResponseEntity.ok(new DefaultResponseDTO(AuthenticationConstants.CREATED, new RegisterResponseDTO(body.name(), body.email(), body.role())));
        }
        return ResponseEntity.badRequest().body(new DefaultResponseDTO(UserConstants.ALREADY_REGISTERED + body.email(), null));
    }

    private User createUserModel(String name, String email, String password, UserRole role) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setRole(role);
        return newUser;
    }

    @HasAdminRole
    @GetMapping("/test/admin")
    public ResponseEntity testAdmin() {
        return ResponseEntity.ok().build();
    }

    @HasOrganizerRole
    @GetMapping("/test/organizer")
    public ResponseEntity testOrganizer() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/user")
    public ResponseEntity testUser() {
        return ResponseEntity.ok().build();
    }
}
