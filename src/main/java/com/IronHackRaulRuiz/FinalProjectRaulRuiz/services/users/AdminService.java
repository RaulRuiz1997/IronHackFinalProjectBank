package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CheckingService;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CreditCardService;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    SavingsService savingsService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    CheckingService checkingService;

    // Método para crear una Savings Account
    public Savings createSavingAccount(Savings savingsAccount) {
        return savingsService.createSavingAccount(savingsAccount);
    }

    // Método para crear una Credit Card Account
    public CreditCard createCreditCardAccount(CreditCard creditCardAccount) {
        return creditCardService.createCreditCardAccount(creditCardAccount);
    }

    // Método para crear una Checking Account
    public Account createCheckingAccount(Checking checkingAccount) {
        return checkingService.createCheckingAccount(checkingAccount);
    }

}
