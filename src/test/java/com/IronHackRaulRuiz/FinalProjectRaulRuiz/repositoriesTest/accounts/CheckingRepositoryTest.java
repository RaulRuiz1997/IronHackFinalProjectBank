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

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CheckingRepositoryTest {

    // todo: Los test se hacen asi? Jose te dijo que el método admin.createCheckingAccount() debería tener la lógica en el Service

    // todo: si no hay metodos especificos en los repositorios, hacer testing de los CRUD (CREATE, READ, UPDATE Y DELETE)

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    // Test para crear y guardar un Checking Account en la BBDD
    @BeforeEach
    void setUp() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", "12345", LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("7500.2"), accountHolderRaul, null, StatusAccount.FROZEN, "01101010100H");

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

        if (checkingRepository.findById(1L).isPresent()) {

            checkingAccount = checkingRepository.findById(1L).get();

            assertEquals("Raul", checkingAccount.getPrimaryOwner().getName());

        }

        // LANZAR UN THROW

        // todo: debería haber un else, no? ya que si no esta presente en el if no entra y te dará que ha pasado el test

    }

    // Test para verificar el método setBalance()
    @Test
    void ShouldSetBalanceWithPenaltyFee() {

        Checking checkingAccount;

        if (checkingRepository.findById(1L).isPresent()) {

            checkingAccount = checkingRepository.findById(1L).get();

            checkingAccount.setBalance(new BigDecimal("240.0"));

            checkingRepository.save(checkingAccount);

            assertEquals(200, checkingRepository.findById(checkingAccount.getId()).get().getBalance());

        }

    }

}
