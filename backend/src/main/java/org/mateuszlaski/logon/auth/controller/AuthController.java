package org.mateuszlaski.logon.auth.controller;

import jakarta.validation.Valid;
import org.mateuszlaski.logon.auth.dto.RegisterRequest;
import org.mateuszlaski.logon.auth.dto.RegisterResponse;
import org.mateuszlaski.logon.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponse> registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.registerAccount(registerRequest), HttpStatus.CREATED);
    }
}
