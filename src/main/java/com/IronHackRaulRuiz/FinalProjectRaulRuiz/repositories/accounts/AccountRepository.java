package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByPrimaryOwner(String name);
    Optional<Account> findBySecondaryOwner(String name);

}
