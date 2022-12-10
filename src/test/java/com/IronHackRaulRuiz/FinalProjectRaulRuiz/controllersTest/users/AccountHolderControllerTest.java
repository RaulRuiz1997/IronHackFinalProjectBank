package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transactions.Transaction;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTest {

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

    // todo @AuthenticationPrincipal
    // GET -> /account-holder/get-balance/{id}
    @Test
    void shouldGetBalance() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA1", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(get("/account-holder/get-balance/{id}", savingAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(savingAccount.getBalance().toString()));

    }

    // PATCH -> /account-holder/set-balance
    @Test
    void shouldSetBalance() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA2", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);

        savingAccount.setBalance(BigDecimal.valueOf(1000.0));

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(patch("/account-holder/set-balance")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // todo esto esta bien comprobado?
        assertTrue(result.getResponse().getContentAsString().contains("\"balance\":1000.0"));

    }

    // todo @AuthenticationPrincipal
    // todo: mirar esta prueba a ver si esta bien planteada
    // POST -> localhost:8080/account-holder/send-money/{id}
    @Test
    void shouldSendMoney() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("PETER PRUEBA3", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        Savings savingAccount = new Savings(new BigDecimal("21500.00"), accountHolder, null, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        userRepository.save(accountHolder);

        savingsRepository.save(savingAccount);


        Transaction transaction = new Transaction(1L, savingAccount.getId(), savingAccount.getPrimaryOwner().getName(), savingAccount.getBalance());

        String body = objectMapper.writeValueAsString(transaction);

        MvcResult result = mockMvc.perform(post("/account-holder/send-money/{id}", savingAccount.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("\"balance\":" + savingAccount.getBalance()));

    }

}
