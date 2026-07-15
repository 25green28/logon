package org.mateuszlaski.logon.repository;

import org.mateuszlaski.logon.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
