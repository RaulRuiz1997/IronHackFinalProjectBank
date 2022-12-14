package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
