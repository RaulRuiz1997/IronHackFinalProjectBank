package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
