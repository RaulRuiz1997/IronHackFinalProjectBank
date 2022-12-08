package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositoriesTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreditCardRepositoryTest {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    // Test para crear y guardar un Checking Account en la BBDD
    @BeforeEach
    void setUp() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

    }

    // Test para limpiar toda la BBDD
    @AfterEach
    void clean() {

        accountHolderRepository.deleteAll();

        creditCardRepository.deleteAll();

    }

    // Test para buscar por ID
    @Test
    void ShouldFindSavingAccountById() {

        CreditCard creditCardAccount;

        if (creditCardRepository.findById(1L).isPresent()) {

            creditCardAccount = creditCardRepository.findById(1L).get();

            assertEquals("Raul", creditCardAccount.getPrimaryOwner().getName());

        }

    }

    // Test para verificar que no se pueda meter el valor (creditLimit) menor que el mínimo establecido (100)
    @Test
    void ShouldSetCreditLimitAtMinimum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul2", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el balance mínimo por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        creditCardAccount.setCreditLimit(90);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(100, creditCardRepository.findById(accountHolderRaul.getId()).get().getCreditLimit());

        }

    }

    // Test para verificar que no se pueda meter el valor (creditLimit) mayor que el máximo establecido (100000)
    @Test
    void ShouldSetCreditLimitAtMaximum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul3", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el balance mínimo por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        creditCardAccount.setCreditLimit(120000);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(100000, creditCardRepository.findById(accountHolderRaul.getId()).get().getCreditLimit());

        }

    }

    // Test para verificar que no se pueda meter el valor (interestRate) menor que el mínimo establecido (0.1)
    @Test
    void ShouldSetInterestRateAtMinimum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul4", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el interestRate mínimo por debajo del mínimo permitido, entonces lo pondrá al mínimo en vez del que le intentan meter
        creditCardAccount.setInterestRate(new BigDecimal("0.01"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0.1, creditCardRepository.findById(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar que no se pueda meter el valor (interestRate) mayor que el máximo establecido (0.2)
    @Test
    void ShouldSetInterestRateAtMaximum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el interestRate por debajo del máximo permitido, entonces lo pondrá al máximo en vez del que le intentan meter
        creditCardAccount.setInterestRate(new BigDecimal("0.3"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0.2, creditCardRepository.findById(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

}
