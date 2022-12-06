package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public List<AccountHolder> findAll() {
        return accountHolderRepository.findAll();
    }

    public AccountHolder createCheckingAccount(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

}
