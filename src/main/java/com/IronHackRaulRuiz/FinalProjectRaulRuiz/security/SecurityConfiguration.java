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

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {

        return authConf.getAuthenticationManager();

    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.authorizeHttpRequests()
                // Rutas que los ACCOUNT HOLDER y ADMIN pueden acceder
                .requestMatchers(HttpMethod.GET, "/account-holder/get-balance/**").hasAnyRole("ACCOUNT-HOLDER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/account-holder/send-money/**").hasAnyRole("ACCOUNT-HOLDER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/third-party/move-money/**").hasAnyRole("THIRD-PARTY-USER", "ADMIN")
                // Rutas que solo los ADMIN pueden acceder
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
