package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
