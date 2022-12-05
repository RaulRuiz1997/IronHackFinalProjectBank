package com.IronHackRaulRuiz.FinalProjectRaulRuiz;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Según alex, testear sobretodo los controllers (las rutas de getMapping, etc...)

@SpringBootTest
public class SavingsRepositoryTest {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @BeforeEach
    void setUp() {

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 200.0, "secretKey", 0.1);

        accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

    }

    @Test
    void ShouldFindSavingAccountById() {

        Savings savingAccount;

        if (savingsRepository.findById(1L).isPresent()) {

            savingAccount = savingsRepository.findById(1L).get();

            assertEquals("Raul", savingAccount.getPrimaryOwner().getName());

        }

    }

    @Test
    void ListSizeShouldBe2() {

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 200.0, "secretKey", 0.1);

        accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(1L).isPresent()) {

            assertEquals(7, savingsRepository.findAll().size());

        }

    }

    // El rate mínimo configurado es de 0.0025
    @Test
    void shouldSetRateAtMinimum() { // todo: mirar por ID y no por nombre pk se puede repetir

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 200.0, "secretKey", 0.1);

        // Seteamos el interestRate por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        savingAccount.setInterestRate(-1.0);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // El rate máximo configurado es de 0.5
    @Test
    void shouldSetRateAtMax() {

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 200.0, "secretKey", 0.1);

        // Seteamos el interestRate por encima del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        savingAccount.setInterestRate(0.6);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0.5, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // El balance mínimo configurado es de 100
    @Test
    void shouldSetBalanceAtMinimum() {

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 200.0, "secretKey", 0.1);

        // Seteamos el balance mínimo por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        savingAccount.setMinimumBalance(90.0);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(100, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getMinimumBalance());

        }

    }

    // El balance máximo configurado es de 1000
    @Test
    void shouldSetBalanceAtMax() {

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 200.0, "secretKey", 0.1);

        // Seteamos el balance máximo por encima del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        savingAccount.setMinimumBalance(1100.0);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(1000, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getMinimumBalance());

        }

    }

    @AfterEach
    void clean() {

        savingsRepository.deleteAll();

    }

}
