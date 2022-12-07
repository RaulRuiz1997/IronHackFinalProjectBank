package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.*;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users.AccountHolderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account-holder")
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    AccountService accountService;

    // GET -> localhost:8080/account-holder/all
    // Método para encontrar todos los Account Holders
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> findAll() {
        return accountHolderService.findAll();
    }

    // POST -> localhost:8080/account-holder/add
    /*
    {
        "name": "Antonio",
        "dateOfBirth": "1965-02-23",
        "primaryAddress": {
            "name": "Calle felichia",
            "numberHouse": 72,
            "city": "Montornes",
            "zipCode": 81027
        },
        "mailingAddress": null
    }
    */
    // Método para añadir un Account Holders
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
        return accountHolderService.createAccountHolder(accountHolder);
    }

    // Admins should be able to access the balance for any account and to modify it.
    // GET -> localhost:8080/account-holder/get-balance/1
    // Método para obtener el balance de una cuenta mediante ID
    // @AuthenticationPrincipal UserDetails userDetails -> Aquí tenemos el usuario y la contra del usuario que se ha logeado
    @GetMapping("/get-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) { // todo: manu dice que el id sobra
        return accountService.getBalance(id, userDetails);
    }

    // PATCH -> localhost:8080/account-holder/set-balance/1
    /*

    */
    // Método para actualizar el balance de una cuenta mediante su ID
    @PatchMapping("/set-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account setBalance(@PathVariable Long id, @RequestBody BigDecimal balance) {
        return accountService.setBalance(id, balance);
    }

    //Método para transferir de una cuenta a otra cuenta
    // todo: No se si esta bien, si es un patch, si tiene que devolver algo o no, si le tengo que enviar un request body
    /*@PatchMapping("/send-money/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sendMoney(@PathVariable Long id, @RequestBody BigDecimal balance) {
        accountService.sendMoney();
    }*/

}
