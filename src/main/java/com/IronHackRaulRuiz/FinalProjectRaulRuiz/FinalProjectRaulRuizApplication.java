package com.IronHackRaulRuiz.FinalProjectRaulRuiz;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class FinalProjectRaulRuizApplication implements CommandLineRunner {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CheckingRepository checkingRepository;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectRaulRuizApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Admin admin = new Admin("Admin");

        Address addressSavings = new Address("C/ Falsa", 123, "BCN", 8100);
        Address addressCreditCard = new Address("Street Oporto", 45, "SANT FRANCISCO", 449982);
        Address addressChecking = new Address("Grove Street", 24, "LOS SANTOS", 11923);

        AccountHolder accountHolderSavingsAccount = new AccountHolder("Peter (S.A.)", LocalDate.of(1997, 12, 19), addressSavings);
        AccountHolder accountHolderCreditCardAccount = new AccountHolder("John (C.C.A)", LocalDate.of(1968, 06, 25), addressCreditCard);
        AccountHolder accountHolderCheckingAccount = new AccountHolder("Phillip (C.A.)", LocalDate.of(1982, 02, 14), addressChecking);

        Savings savingAccount = admin.createSavingAccount(21397.24, accountHolderSavingsAccount, accountHolderCreditCardAccount, StatusAccount.ACTIVE, 999.0, "c1n90n8", 0.2);

        CreditCard creditCardAccount = admin.createCreditCardAccount(914214.2, accountHolderCreditCardAccount, null, StatusAccount.ACTIVE, 89523, 0.015);

        Checking checkingAccount = admin.createCheckingAccount(7500.2, accountHolderCheckingAccount, null, StatusAccount.FROZEN, 2, 0.2,  "01101010100H");

        accountHolderRepository.save(accountHolderSavingsAccount);
        accountHolderRepository.save(accountHolderCreditCardAccount);
        accountHolderRepository.save(accountHolderCheckingAccount);

        savingsRepository.save(savingAccount);

        creditCardRepository.save(creditCardAccount);

        checkingRepository.save(checkingAccount);

    }

}
