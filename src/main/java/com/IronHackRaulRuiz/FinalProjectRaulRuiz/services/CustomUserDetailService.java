package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    // @AuthenticationPrincipal buscar

    // Spring carga este método cuando te loggeas
    // El nombre de cada usuario debe ser unico
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return new CustomUserDetails(user);

    }

}
