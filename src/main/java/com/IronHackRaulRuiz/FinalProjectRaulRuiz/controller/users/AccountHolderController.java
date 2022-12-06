package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-holder")
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    // GET -> localhost:8080/account-holder/all
    // Método para encontrar todos los Account Holders
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> findAll() {
        return accountHolderService.findAll();
    }

    // POST -> localhost:8080/account-holder
    // Método para añadir un Account Holders
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHolderService.createCheckingAccount(accountHolder);
    }

}
