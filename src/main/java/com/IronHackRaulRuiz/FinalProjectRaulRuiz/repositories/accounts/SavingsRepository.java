package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
