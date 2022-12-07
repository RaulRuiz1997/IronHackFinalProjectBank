package com.IronHackRaulRuiz.FinalProjectRaulRuiz.security;

import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration {

    // todo: estos 3 metodos se tienen que usar en algun lado?
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {

        return authConf.getAuthenticationManager();

    }

    // todo: No se si esto esta bien
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // ---ADMINS---
        // Admins should be able to access the balance for any account and to modify it.

        // ---ACCOUNT HOLDERS---
        //AccountHolders should be able to access their own account balance
        // Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner).
        // The transfer should only be processed if the account has sufficient funds. The user must provide the Primary or Secondary owner’s name and the id of the account that should receive the transfer.

        //---THIRD-PARTY USERS---
        //There must be a way for third-party users to receive and send money to other accounts.
        //Third-party users must be added to the database by an admin.
        //In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request. They also must provide the amount, the Account id and the account secret key.

        httpSecurity.httpBasic();

        // todo: Mirar si los admins también tienen que tener HttpMethod.POST, PUT, PATCH, DELETE (SI, PONER TODOS)
        // todo: El usuario Jaume puede añadir account-users, porque? El get all no puede pero añadir si wtf
        httpSecurity.authorizeHttpRequests()

                .requestMatchers(HttpMethod.GET, "/account-holder/get-balance/**").hasAnyRole("ACCOUNT-HOLDER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/account-holder/send-money").hasRole("ACCOUNT-HOLDER")

                .requestMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }

}
