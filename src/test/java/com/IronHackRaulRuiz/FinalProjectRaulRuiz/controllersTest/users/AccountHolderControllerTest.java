package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired
    UserRepository userRepository;

    // Test para crear y guardar un Checking Account en la BBDD
    @BeforeEach
    void setUp() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolderRaul = new AccountHolder("Raul", "12345", LocalDate.of(1997, 12, 19), address, null);

        userRepository.save(accountHolderRaul);

    }

    // Test para limpiar toda la BBDD
    @AfterEach
    void clean() {

        //userRepository.deleteAll();

    }

    @Test
    void TestPrueba() {

    }

}
