package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositoriesTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CheckingRepositoryTest {

    // todo: Los test se hacen asi? Jose te dijo que el método admin.createCheckingAccount() debería tener la lógica en el Service

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    // Test para crear y guardar un Checking Account en la BBDD
    @BeforeEach
    void setUp() {

        Admin admin = new Admin("Admin");

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Checking checkingAccount = admin.createCheckingAccount(500.0, accountHolderRaul, null, StatusAccount.ACTIVE, 2, 0.1,  "secretKey");

        accountHolderRepository.save(accountHolderRaul);

        checkingRepository.save(checkingAccount);

    }

    // Test para limpiar toda la BBDD
    @AfterEach
    void clean() {

        checkingRepository.deleteAll();

    }

    // Test para buscar por ID
    @Test
    void ShouldFindSavingAccountById() {

        Checking checkingAccount;

        if (checkingRepository.findById(1L).isPresent()) {

            checkingAccount = checkingRepository.findById(1L).get();

            assertEquals("Raul", checkingAccount.getPrimaryOwner().getName());

        }

        // todo: debería haber un else, no? ya que si no esta presente en el if no entra y te dará que ha pasado el test

    }

}
