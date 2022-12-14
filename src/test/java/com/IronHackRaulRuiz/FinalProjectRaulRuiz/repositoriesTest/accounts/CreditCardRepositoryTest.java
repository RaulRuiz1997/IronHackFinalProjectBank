package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositoriesTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
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

    CreditCard creditCardAccount;

    // Test para crear y guardar un Credit Card Account en la BBDD
    @BeforeEach
    void setUp() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", "12345", LocalDate.of(1997, 12, 19), address, null);

        creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

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

        if (creditCardRepository.findById(this.creditCardAccount.getId()).isPresent()) {

            creditCardAccount = creditCardRepository.findById(this.creditCardAccount.getId()).get();

            assertEquals("Raul", creditCardAccount.getPrimaryOwner().getName());

        }

    }

    // Test para verificar que no se pueda meter el valor (creditLimit) menor que el m??nimo establecido (100)
    @Test
    void ShouldSetCreditLimitAtMinimum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul2", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el balance m??nimo por debajo del m??nimo permitido, entonces lo pondr?? al m??nimo en vez del que le intentan meter
        creditCardAccount.setCreditLimit(90);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(100, creditCardRepository.findById(accountHolderRaul.getId()).get().getCreditLimit());

        }

    }

    // Test para verificar que no se pueda meter el valor (creditLimit) mayor que el m??ximo establecido (100000)
    @Test
    void ShouldSetCreditLimitAtMaximum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul3", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el balance m??nimo por debajo del m??nimo permitido, entonces lo pondr?? al m??nimo en vez del que le intentan meter
        creditCardAccount.setCreditLimit(120000);

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(100000, creditCardRepository.findById(accountHolderRaul.getId()).get().getCreditLimit());

        }

    }

    // Test para verificar que no se pueda meter el valor (interestRate) menor que el m??nimo establecido (0.1)
    @Test
    void ShouldSetInterestRateAtMinimum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul4", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el interestRate m??nimo por debajo del m??nimo permitido, entonces lo pondr?? al m??nimo en vez del que le intentan meter
        creditCardAccount.setInterestRate(new BigDecimal("0.01"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0.1, creditCardRepository.findById(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar que no se pueda meter el valor (interestRate) mayor que el m??ximo establecido (0.2)
    @Test
    void ShouldSetInterestRateAtMaximum() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        // Seteamos el interestRate por debajo del m??ximo permitido, entonces lo pondr?? al m??ximo en vez del que le intentan meter
        creditCardAccount.setInterestRate(new BigDecimal("0.3"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            assertEquals(0.2, creditCardRepository.findById(accountHolderRaul.getId()).get().getInterestRate());

        }

    }

    // Test para verificar el interest rate no est?? aplicado en el m??todo getBalance()
    @Test
    void ShouldGetBalanceUpdatedWithoutInterestRateApplied() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            // Si pasase 1 mes deber??a estar aplicado el interest rate, pero como no ha pasado da el balance sin el interest rate aplicado
            assertEquals(new BigDecimal("500.0"), creditCardAccount.getBalance());

        }

    }

    // Test para verificar el interest rate no est?? aplicado en el m??todo setBalance()
    @Test
    void ShouldSetBalanceUpdatedWithoutInterestRateApplied() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul5", "12345", LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("500.0"), accountHolderRaul, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        accountHolderRaul = accountHolderRepository.save(accountHolderRaul);

        creditCardAccount.setBalance(new BigDecimal("777.0"));

        creditCardRepository.save(creditCardAccount);

        if (creditCardRepository.findById(accountHolderRaul.getId()).isPresent()) {

            // Si pasase 1 mes deber??a estar aplicado el interest rate, pero como no ha pasado da el balance sin el interest rate aplicado
            assertEquals(new BigDecimal("777.0"), creditCardAccount.getBalance());

        }

    }

}
