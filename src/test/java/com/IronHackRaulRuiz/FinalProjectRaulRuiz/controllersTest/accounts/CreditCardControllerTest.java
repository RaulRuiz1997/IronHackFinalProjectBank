package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreditCardControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    // GET -> /credit-card/id/{id}
    // Test para encontrar una cuenta Credit Card por id mediante @PathVariable
    @Test
    void ShouldFindByIdPathVariable() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolder, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        userRepository.save(accountHolder);

        creditCardRepository.save(creditCardAccount);

        String body = objectMapper.writeValueAsString(creditCardAccount);

        MvcResult result = mockMvc.perform(get("/credit-card/id/{id}", creditCardAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA"));

    }

    // GET -> /credit-card/id?id=1
    // Test para encontrar una cuenta Credit Card por id mediante @RequestParam
    @Test
    void ShouldFindByIdRequestParam() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA2", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolder, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        userRepository.save(accountHolder);

        creditCardRepository.save(creditCardAccount);

        String body = objectMapper.writeValueAsString(creditCardAccount);

        MvcResult result = mockMvc.perform(get("/credit-card/id")
                        .param("id", creditCardAccount.getId().toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA2"));

    }

    // POST -> /credit-card/add
    // Test para crear una cuenta Credit Card
    @Test
    void ShouldCreateCreditCardAccount() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PRUEBA", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolder, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        userRepository.save(accountHolder);

        creditCardRepository.save(creditCardAccount);

        String body = objectMapper.writeValueAsString(creditCardAccount);

        MvcResult result = mockMvc.perform(post("/credit-card/add")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PRUEBA"));

    }

    // PUT -> /credit-card/update-put
    // Test para actualizar una cuenta Credit Card mediante el método HTTP PUT
    @Test
    void ShouldUpdateCreditCardAccountPut() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA3", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolder, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        userRepository.save(accountHolder);

        creditCardRepository.save(creditCardAccount);

        String body = objectMapper.writeValueAsString(creditCardAccount);

        MvcResult result = mockMvc.perform(put("/credit-card/update-put")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA3"));

    }

    // PATCH -> /credit-card/update-patch
    // Test para actualizar una cuenta Credit Card mediante el método HTTP PATCH
    @Test
    void ShouldUpdateCreditCardAccountPatch() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA5", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolder, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        userRepository.save(accountHolder);

        creditCardRepository.save(creditCardAccount);

        String body = objectMapper.writeValueAsString(creditCardAccount);

        MvcResult result = mockMvc.perform(patch("/credit-card/update-patch")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("PETER PRUEBA5"));

    }

    // DELETE -> /credit-card/delete/{id}
    // Test para eliminar una cuenta Credit Card
    @Test
    void ShouldDeleteCreditCardAccountById() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA6", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        CreditCard creditCardAccount = new CreditCard(new BigDecimal("914214.2"), accountHolder, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        userRepository.save(accountHolder);

        creditCardRepository.save(creditCardAccount);

        String body = objectMapper.writeValueAsString(creditCardAccount);

        MvcResult result = mockMvc.perform(delete("/credit-card/delete/{id}", creditCardAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertFalse(result.getResponse().getContentAsString().contains("PETER PRUEBA6"));

    }

}
