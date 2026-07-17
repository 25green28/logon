package org.mateuszlaski.logon.account.repository;

import org.mateuszlaski.logon.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByEmail(String email);
}
