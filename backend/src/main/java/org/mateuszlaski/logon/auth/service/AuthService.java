package org.mateuszlaski.logon.auth.service;

import org.mateuszlaski.logon.account.entity.Account;
import org.mateuszlaski.logon.account.repository.AccountRepository;
import org.mateuszlaski.logon.auth.dto.RegisterRequest;
import org.mateuszlaski.logon.auth.dto.RegisterResponse;
import org.mateuszlaski.logon.common.exception.EmailAlreadyInUseException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    AccountRepository accountRepository;

    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public RegisterResponse registerAccount(RegisterRequest registerRequest) {
        if (accountRepository.existsAccountByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyInUseException(registerRequest.getEmail());
        }

        Account accountToAdd = new Account();
        accountToAdd.setEmail(registerRequest.getEmail());
        accountToAdd.setUsername(registerRequest.getUsername());
        accountToAdd.setPassword(registerRequest.getPassword());
        accountToAdd.setCreatedAt(LocalDateTime.now());
        accountToAdd.setEnabled(true);

        accountRepository.save(accountToAdd);
        return new RegisterResponse("Account created successfully");
    }
}
