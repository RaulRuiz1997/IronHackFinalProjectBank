package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
    Optional<Savings> findByPrimaryOwnerId(Long id);

}
