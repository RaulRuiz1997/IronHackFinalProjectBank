package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.roles.Role;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.ThirdParty;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.ThirdPartyRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    UserRepository userRepository;

    public ThirdParty createThirdPartyUser(ThirdParty thirdPartyUser, UserDetails userDetails) {

        boolean isAdmin = false;

        // Obtenemos el usuario para saber si es ADMIN o no
        User user = userRepository.findByName(userDetails.getUsername()).get();

        for (Role role : user.getRoles()) {

            if (role.getRole().equals("ADMIN")) isAdmin = true;

        }

        // Validamos que solo los admins puedan crear Third Party Users
        if (isAdmin) {

            return thirdPartyRepository.save(thirdPartyUser);

        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario no es un Admin");

    }

    public List<ThirdParty> findAll() {
        return thirdPartyRepository.findAll();
    }

}
