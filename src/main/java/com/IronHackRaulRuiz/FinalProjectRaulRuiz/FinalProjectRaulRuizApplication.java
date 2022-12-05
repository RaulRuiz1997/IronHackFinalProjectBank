package com.IronHackRaulRuiz.FinalProjectRaulRuiz;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectRaulRuizApplication implements CommandLineRunner {

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

        /*
        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        User accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, 0.0, 200.0, "secretKey", LocalDate.of(2015, 5, 5), StatusAccount.ACTIVE, 0.010);

        CreditCard creditCardAccount = admin.createCreditCardAccount(accountHolderRaul);

        Checking checkingAccount = admin.createCheckingAccount(accountHolderRaul);

        savingsRepository.save(savingAccount);

        creditCardRepository.save(creditCardAccount);

        checkingRepository.save(checkingAccount);

        */

    }

}
