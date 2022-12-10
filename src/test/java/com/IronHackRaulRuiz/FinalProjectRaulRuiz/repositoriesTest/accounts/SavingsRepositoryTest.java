package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositoriesTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
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

@SpringBootTest
public class SavingsRepositoryTest {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    Savings savingAccount;

    // Test para crear y guardar un Saving Account en la BBDD
    @BeforeEach
    void setUp() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", "12345", LocalDate.of(1997, 12, 19), address, null);

        savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

    }

    // Test para limpiar toda la BBDD
    @AfterEach
    void clean() {

        accountHolderRepository.deleteAll();

        savingsRepository.deleteAll();

    }

    // Test para buscar por ID
    @Test
    void ShouldFindSavingAccountById() {

        Savings savingAccount;

        if (savingsRepository.findById(this.savingAccount.getId()).isPresent()) {

            savingAccount = savingsRepository.findById(this.savingAccount.getId()).get();

            assertEquals("Raul", savingAccount.getPrimaryOwner().getName());

        }

    }

    // Test para verificar el size de la lista
    @Test
    void ListSizeShouldBe2() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul2", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(2, savingsRepository.findAll().size());

        }

    }

    // Test para verificar que no se pueda meter el valor (rate) menor que el mínimo establecido (0.0025)
    @Test
    void ShouldSetRateAtMinimum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul3", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el interestRate por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        savingAccount.setInterestRate(new BigDecimal("-1.0"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(new BigDecimal("0.00"), savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar que no se pueda meter el valor (rate) mayor que el máximo establecido (0.5)
    @Test
    void ShouldSetRateAtMax() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul4", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el interestRate por encima del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        savingAccount.setInterestRate(new BigDecimal("0.6"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(new BigDecimal("0.50"), savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar que no se pueda meter el valor (balance) menor que el mínimo establecido (100)
    @Test
    void ShouldSetBalanceAtMinimum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el balance mínimo por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        savingAccount.setMinimumBalance(new BigDecimal("90.0"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(new BigDecimal("100.00"), savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getMinimumBalance());

        }

    }

    // Test para verificar que no se pueda meter el valor (balance) mayor que el máximo establecido (1000)
    @Test
    void ShouldSetBalanceAtMax() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul6", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        // Seteamos el balance máximo por encima del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        savingAccount.setMinimumBalance(new BigDecimal("1100.0"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).isPresent()) {

            assertEquals(new BigDecimal("1000.00"), savingsRepository.findByPrimaryOwnerId(accountHolderRaul.getId()).get().getMinimumBalance());

        }

    }
    
    @Test
    void ShouldGetBalanceUpdatedWithoutInterestRateApplied() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(accountHolderRaul.getId()).isPresent()) {

            // Si pasase 1 año debería estar aplicado el interest rate, pero como no ha pasado da el balance sin el interest rate aplicado
            assertEquals(new BigDecimal("500.0"), savingAccount.getBalance());

        }

    }

    @Test
    void ShouldSetBalanceUpdatedWithoutInterestRateApplied() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, new BigDecimal("200.0"), "secretKey", new BigDecimal("0.1"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        savingAccount.setBalance(new BigDecimal("777.0"));

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(accountHolderRaul.getId()).isPresent()) {

            // Si pasase 1 mes debería estar aplicado el interest rate, pero como no ha pasado da el balance sin el interest rate aplicado
            assertEquals(new BigDecimal("777.0"), savingAccount.getBalance());

        }

    }

}
