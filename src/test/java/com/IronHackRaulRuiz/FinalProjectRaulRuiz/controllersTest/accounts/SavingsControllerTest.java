package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CreditCardService;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.SavingsService;
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
public class SavingsControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SavingsRepository savingsRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    // GET -> /savings/id/{id}
    @Test
    void ShouldFindByIdPathVariable() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(get("/savings/id/{id}", savingAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA"));

    }

    // GET -> /savings/id?id=1
    @Test
    void ShouldFindByIdRequestParam() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA2", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(get("/savings/id")
                        .param("id", savingAccount.getId().toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA2"));

    }

    // POST -> /savings/add
    @Test
    void ShouldCreateSavingsAccount() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PRUEBA", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(post("/savings/add")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PRUEBA"));

    }

    // PUT -> /savings/update-put
    @Test
    void ShouldUpdateSavingsAccountPut() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("hola", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(put("/savings/update-put")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("hola"));

    }

    // PATCH -> /savings/update-patch
    @Test
    void ShouldUpdateSavingsAccountPatch() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("HEY", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(patch("/savings/update-patch")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("HEY"));

    }

    // DELETE -> /savings/delete/{id}
    @Test
    void ShouldDeleteSavingsAccountById() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("Ocul", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(delete("/savings/delete/{id}", savingAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertFalse(result.getResponse().getContentAsString().contains("Ocul"));

    }

}
