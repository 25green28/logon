package org.mateuszlaski.logon.security.service;

import org.jspecify.annotations.NullMarked;
import org.mateuszlaski.logon.account.entity.Account;
import org.mateuszlaski.logon.account.repository.AccountRepository;
import org.mateuszlaski.logon.security.userdetails.AccountUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
        return new AccountUserDetails(account);
    }
}
