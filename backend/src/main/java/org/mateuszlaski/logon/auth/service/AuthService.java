package org.mateuszlaski.logon.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mateuszlaski.logon.account.entity.Account;
import org.mateuszlaski.logon.account.repository.AccountRepository;
import org.mateuszlaski.logon.auth.dto.LoginRequest;
import org.mateuszlaski.logon.auth.dto.LoginResponse;
import org.mateuszlaski.logon.auth.dto.RegisterRequest;
import org.mateuszlaski.logon.auth.dto.RegisterResponse;
import org.mateuszlaski.logon.common.exception.EmailAlreadyInUseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(AccountRepository accountRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       SecurityContextRepository securityContextRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        if (accountRepository.existsAccountByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyInUseException(registerRequest.getEmail());
        }

        Account accountToAdd = new Account();
        accountToAdd.setEmail(registerRequest.getEmail());
        accountToAdd.setUsername(registerRequest.getUsername());
        accountToAdd.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        accountToAdd.setCreatedAt(LocalDateTime.now());
        accountToAdd.setEnabled(true);

        accountRepository.save(accountToAdd);
        return new RegisterResponse("Account created successfully");
    }

    public Authentication login(LoginRequest loginRequest) {
        Authentication token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return authenticationManager.authenticate(token);
    }
}
