package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.StudentChecking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.StudentCheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CreditCardService;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.SavingsService;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.StudentCheckingService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class StudentCheckingControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    // GET -> /student-checking/id/{id}
    @Test
    void ShouldFindByIdPathVariable() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("ESTUDIANTE", passwordEncoder.encode("estudiante"), LocalDate.of(2010, 12, 19), address, null);

        // Guardamos un CheckingAccount con una edad menor a 24 años para que sea un StudentCheckingAccount
        StudentChecking studentChecking = new StudentChecking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        studentCheckingRepository.save(studentChecking);

        String body = objectMapper.writeValueAsString(studentChecking);

        MvcResult result = mockMvc.perform(get("/student-checking/id/{id}", studentChecking.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ESTUDIANTE"));

    }

    // GET -> /student-checking/id?id=1
    @Test
    void ShouldFindByIdRequestParam() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("ESTUDIANTE2", passwordEncoder.encode("estudiante2"), LocalDate.of(2010, 12, 19), address, null);

        StudentChecking studentChecking = new StudentChecking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        studentCheckingRepository.save(studentChecking);

        String body = objectMapper.writeValueAsString(studentChecking);

        MvcResult result = mockMvc.perform(get("/student-checking/id")
                        .param("id", studentChecking.getId().toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ESTUDIANTE2"));

    }

    // DELETE -> /student-checking/delete/{id}
    @Test
    void ShouldDeleteStudentCheckingAccount() throws Exception {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        AccountHolder accountHolder = new AccountHolder("ESTUDIANTE3", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), address, null);

        StudentChecking studentChecking = new StudentChecking(new BigDecimal("1000.0"), accountHolder, null, StatusAccount.FROZEN, "SECRET KEY");

        userRepository.save(accountHolder);

        studentCheckingRepository.save(studentChecking);

        String body = objectMapper.writeValueAsString(studentChecking);

        MvcResult result = mockMvc.perform(delete("/student-checking/delete/{id}", studentChecking.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertFalse(result.getResponse().getContentAsString().contains("ESTUDIANTE3"));

    }

}
