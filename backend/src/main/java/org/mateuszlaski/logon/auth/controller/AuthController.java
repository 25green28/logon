package org.mateuszlaski.logon.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.mateuszlaski.logon.account.entity.Account;
import org.mateuszlaski.logon.auth.dto.*;
import org.mateuszlaski.logon.auth.service.AuthService;
import org.mateuszlaski.logon.auth.service.SessionAuthService;
import org.mateuszlaski.logon.security.userdetails.AccountUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AuthController {
    private final AuthService authService;
    private final SessionAuthService sessionAuthService;

    public AuthController(AuthService authService, SessionAuthService sessionAuthService) {
        this.authService = authService;
        this.sessionAuthService = sessionAuthService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponse> registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginAccount(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {

        Authentication auth =  authService.login(loginRequest);
        sessionAuthService.authenticate(auth, request, response);

        return ResponseEntity.ok(new LoginResponse("Authenticated successfully"));
    }

    @GetMapping("/whoami")
    public ResponseEntity<WhoamiResponse> whoami(@AuthenticationPrincipal AccountUserDetails accountUserDetails) {
        Account account = accountUserDetails.getAccount();

        var whoamiResponse = new WhoamiResponse(
                account.getUsername(),
                account.getEmail()
        );

        return ResponseEntity.ok(whoamiResponse);
    }
}
