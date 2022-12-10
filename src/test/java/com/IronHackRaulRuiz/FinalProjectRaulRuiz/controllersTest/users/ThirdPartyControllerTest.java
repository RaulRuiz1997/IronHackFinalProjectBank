package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controllersTest.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.ThirdParty;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AdminRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.RoleRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.ThirdPartyRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartyControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private boolean firstTime = true;

    @BeforeEach
    void setUp() {

        roleRepository.deleteAll();
        userRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        objectMapper.findAndRegisterModules();

    }


    @Test
    @WithMockUser(username = "user1", password = "pwd")
    void createUser() throws Exception {

        String body = objectMapper.writeValueAsString(new Admin("administrador","1234"));
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/admin/")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

    }

    // Método para crear Third Party Users (Solo pueden los Admins)
    @Test
    @WithMockUser(username = "administrador", password = "1234")
    void shouldCreateThirdPartyUser() throws Exception {

        ThirdParty thirdParty = new ThirdParty("ThirdPartyUser", "password", "HK-2");
        thirdPartyRepository.save(thirdParty);

        String body = objectMapper.writeValueAsString(thirdParty);

        MvcResult result = mockMvc.perform(post("/third-party/add")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("name"));

    }

    // Método para transferir dinero de una cuenta a otra cuenta
    @Test
    @WithMockUser(username = "ThirdPartyUser", password = "password")
    void shouldMoveMoney() throws Exception {

        ThirdParty thirdParty = new ThirdParty("ThirdPartyUser", "password", "HK-2");
        thirdPartyRepository.save(thirdParty);

        String body = objectMapper.writeValueAsString(thirdParty);

        MvcResult result = mockMvc.perform(post("/third-party/move-money")
                        .header("hashedKey", "HK-2")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ThirdPartyUser"));

    }

}
