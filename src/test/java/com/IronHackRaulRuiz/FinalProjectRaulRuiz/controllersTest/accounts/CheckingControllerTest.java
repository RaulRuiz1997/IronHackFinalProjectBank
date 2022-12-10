package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CheckingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CheckingControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckingRepository checkingRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    // GET -> /checking/id/{id}
    @Test
    void ShouldFindByIdPathVariable() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        checkingRepository.save(checkingAccount);

        String body = objectMapper.writeValueAsString(checkingAccount);

        MvcResult result = mockMvc.perform(get("/checking/id/{id}", checkingAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA"));

    }

    // GET -> /checking/id?id=3
    @Test
    void ShouldFindByIdRequestParam() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA2", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        checkingRepository.save(checkingAccount);

        String body = objectMapper.writeValueAsString(checkingAccount);

        MvcResult result = mockMvc.perform(get("/checking/id")
                        .param("id", checkingAccount.getId().toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA2"));

    }

    // POST -> /checking/add
    @Test
    void ShouldCreateCheckingAccount() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA3", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        checkingRepository.save(checkingAccount);

        String body = objectMapper.writeValueAsString(checkingAccount);

        MvcResult result = mockMvc.perform(post("/checking/add")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA3"));

    }

    // PUT -> /checking/update-put
    @Test
    void ShouldUpdateCheckingAccountPut() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA4", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        checkingRepository.save(checkingAccount);

        String body = objectMapper.writeValueAsString(checkingAccount);

        MvcResult result = mockMvc.perform(put("/checking/update-put")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA4"));

    }

    // PATCH -> /checking/update-patch
    @Test
    void ShouldUpdateCheckingAccountPatch() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA5", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        checkingRepository.save(checkingAccount);

        String body = objectMapper.writeValueAsString(checkingAccount);

        MvcResult result = mockMvc.perform(patch("/checking/update-patch")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA5"));

    }

    // DELETE -> /checking/delete/{id}
    @Test
    void ShouldDeleteCheckingAccount() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA6", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Checking checkingAccount = new Checking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        checkingRepository.save(checkingAccount);

        String body = objectMapper.writeValueAsString(checkingAccount);

        MvcResult result = mockMvc.perform(delete("/checking/delete/{id}", checkingAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertFalse(result.getResponse().getContentAsString().contains("PETER PRUEBA6"));

    }

}
