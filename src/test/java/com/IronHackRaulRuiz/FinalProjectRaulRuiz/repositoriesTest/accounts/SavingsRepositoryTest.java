package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositoriesTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Según alex, testear sobretodo los controllers (las rutas de getMapping, etc...)

@SpringBootTest
public class SavingsRepositoryTest {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    // Test para crear y guardar un Saving Account en la BBDD
    @BeforeEach
    void setUp() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

    }

    // Test para limpiar toda la BBDD
    @AfterEach
    void clean() {

        savingsRepository.deleteAll();

    }

    // Test para buscar por ID
    @Test
    void ShouldFindSavingAccountById() {

        Savings savingAccount;

        if (savingsRepository.findById(1L).isPresent()) {

            savingAccount = savingsRepository.findById(1L).get();

            assertEquals("Raul", savingAccount.getPrimaryOwner().getName());

        }

    }

    // Test para verificar el size de la lista
    @Test
    void ListSizeShouldBe2() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(2, savingsRepository.findAll().size());

        }

    }

    // Test para verificar que no se pueda meter el valor (rate) menor que el mínimo establecido (0.0025)
    @Test
    void shouldSetRateAtMinimum() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el interestRate por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        savingAccount.setInterestRate(new BigDecimal("-1.0"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar que no se pueda meter el valor (rate) mayor que el máximo establecido (0.5)
    @Test
    void shouldSetRateAtMax() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el interestRate por encima del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        savingAccount.setInterestRate(new BigDecimal("0.6"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0.5, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar que no se pueda meter el valor (balance) menor que el mínimo establecido (100)
    @Test
    void shouldSetBalanceAtMinimum() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el balance mínimo por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        savingAccount.setMinimumBalance(new BigDecimal("90.0"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(100, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getMinimumBalance());

        }

    }

    // Test para verificar que no se pueda meter el valor (balance) mayor que el máximo establecido (1000)
    @Test
    void shouldSetBalanceAtMax() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el balance máximo por encima del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        savingAccount.setMinimumBalance(new BigDecimal("1100.0"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(1000, savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getMinimumBalance());

        }

    }

    @Test
    void TestMethodInterestRate() {

        Admin admin = new Admin("Admin", "1234");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = admin.createSavingAccount(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(savingAccount.getId()).isPresent()) {

            Savings savingAccountNew = savingsRepository.findById(savingAccount.getId()).get();

            assertEquals(savingAccountNew.getBalance(), savingAccountNew.checkInterestRate(savingAccountNew.getBalance()));

        }

    }

}
