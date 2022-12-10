package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.TransactionThirdPartyUsersDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.ThirdParty;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.AccountService;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users.ThirdPartyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/third-party")
public class ThirdPartyController {

    @Autowired
    AccountService accountService;

    @Autowired
    ThirdPartyService thirdPartyService;

    // GET -> localhost:8080/checking/all
    // Método para encontrar todos los Third Party Users
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> finAll() {
        return thirdPartyService.findAll();
    }

    // POST -> localhost:8080/third-party/add
    /*
    {
        "name": "Jusep",
        "hashedKey": "HK-3"
    }
    */
    // Método para crear Third Party Users (Solo pueden los Admins)
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@RequestBody @Valid ThirdParty thirdPartyUser) {
        return thirdPartyService.createThirdPartyUser(thirdPartyUser);
    }

    // POST -> localhost:8080/third-party/move-money
    /*
     {

        "accountId": 3,
        "amount": 200,
        "secretKey": "SECRET KEY"

    }
    */
    // Método para transferir dinero de una cuenta a otra cuenta
    @PostMapping("/move-money")
    @ResponseStatus(HttpStatus.OK)
    public TransactionThirdPartyUsersDTO moveMoney(@RequestHeader String hashedKey, @RequestBody TransactionThirdPartyUsersDTO transaction, @AuthenticationPrincipal UserDetails userDetails) {
        return accountService.moveMoneyFromThirdPartyUser(hashedKey, transaction, userDetails);
    }

}
