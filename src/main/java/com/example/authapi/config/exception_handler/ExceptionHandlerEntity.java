package com.example.authapi.config.exception_handler;


import com.example.authapi.dto.default_responses.DefaultErrorDTO;
import com.example.authapi.entity.user.exceptions.RoleNotAllowedException;
import com.example.authapi.entity.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerEntity {
    @ExceptionHandler(RoleNotAllowedException.class)
    public ResponseEntity<DefaultErrorDTO> handleRoleNotAllowed(RoleNotAllowedException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new DefaultErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<DefaultErrorDTO> handleUserNotFound(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorDTO(exception.getMessage()));
    }
}
