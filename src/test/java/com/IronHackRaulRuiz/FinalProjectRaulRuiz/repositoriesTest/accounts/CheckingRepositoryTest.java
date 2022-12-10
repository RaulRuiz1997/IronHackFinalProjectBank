package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositoriesTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
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
public class CheckingRepositoryTest {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    Checking checkingAccount;

    // Test para crear y guardar un Checking Account en la BBDD
    @BeforeEach
    void setUp() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", "12345", LocalDate.of(1997, 12, 19), address, null);

        checkingAccount = new Checking(new BigDecimal("7500.2"), accountHolderRaul, null, StatusAccount.FROZEN, "01101010100H");

        accountHolderRepository.save(accountHolderRaul);

        checkingRepository.save(checkingAccount);

    }

    // Test para limpiar toda la BBDD
    @AfterEach
    void clean() {

        accountHolderRepository.deleteAll();

        checkingRepository.deleteAll();

    }

    // Test para buscar por ID
    @Test
    void ShouldFindSavingAccountById() {

        Checking checkingAccount;

        if (checkingRepository.findById(this.checkingAccount.getId()).isPresent()) {

            checkingAccount = checkingRepository.findById(this.checkingAccount.getId()).get();

            assertEquals("Raul", checkingAccount.getPrimaryOwner().getName());

        } else {

            throw new IllegalArgumentException("ID not found");

        }

    }

    // Test para verificar el método setBalance()
    @Test
    void ShouldSetBalanceWithPenaltyFee() {

        Checking checkingAccount;

        if (checkingRepository.findById(this.checkingAccount.getId()).isPresent()) {

            checkingAccount = checkingRepository.findById(this.checkingAccount.getId()).get();

            checkingAccount.setBalance(BigDecimal.valueOf(240.0), BigDecimal.valueOf(250.0));

            checkingRepository.save(checkingAccount);

            // Se debería haber aplicado el penalty fee de 40$
            assertEquals(new BigDecimal("200.00"), checkingRepository.findById(checkingAccount.getId()).get().getBalance());

        } else {

            throw new IllegalArgumentException("ID not found");

        }

    }

}
