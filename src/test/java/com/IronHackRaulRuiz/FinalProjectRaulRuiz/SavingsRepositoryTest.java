package com.IronHackRaulRuiz.FinalProjectRaulRuiz;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SavingsRepositoryTest {

    @Autowired
    SavingsRepository savingsRepository;

    @BeforeEach
    void setUp() {

        Admin admin = new Admin("Admin");

        Savings savingAccount = admin.createSavingAccount();

        savingsRepository.save(savingAccount);

    }

    @Test
    void ShouldFindSavingAccountById() {

        Savings savingAccount;

        if (savingsRepository.findById(1L).isPresent()) {

            savingAccount = savingsRepository.findById(1L).get(); // todo: Ojo mirar el id que no esta bien

            assertEquals("Raul", savingAccount.getPrimaryOwner().getName());

        }

    }

    @Test
    void ListSizeShouldBe3() { // todo: no entiendo porque da 3 si debería de ser 1 por el método clean()

        Admin admin = new Admin("Admin");

        Savings savingAccount = admin.createSavingAccount();

        savingsRepository.save(savingAccount);

        if (savingsRepository.findById(1L).isPresent()) {

            assertEquals(3, savingsRepository.findAll().size());

        }

    }

    //  todo: (testear setear el rate a menos de 0.0025 y a mas de 0.5)
    // El rate mínimo configurado es de 0.0025
    @Test
    void shouldSetRateAtMinimum() {



    }

    // El rate máximo configurado es de 0.5
    @Test
    void shouldSetRateAtMax() {



    }

    // todo: (testear setear el balance a menos de 100 y mas de 1000)
    // El balance mínimo configurado es de 100
    @Test
    void shouldSetBalanceAtMinimum() {



    }

    // El balance máximo configurado es de 1000
    @Test
    void shouldSetBalanceAtMax() {



    }

    @AfterEach
    void clean() {

        savingsRepository.deleteAll();

    }

}
