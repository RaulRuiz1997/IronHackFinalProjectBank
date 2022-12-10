package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.transactions;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
