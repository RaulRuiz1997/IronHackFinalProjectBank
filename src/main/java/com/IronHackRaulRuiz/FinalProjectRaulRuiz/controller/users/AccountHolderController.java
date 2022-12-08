package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.AccountDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transfers.Transfer;
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

    // GET -> localhost:8080/account-holder/get-balance/1
    // Método para obtener el balance de una cuenta mediante ID
    @GetMapping("/get-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) { // todo: manu dice que el id sobra
        return accountService.getBalance(id, userDetails);
    }

    // PATCH -> localhost:8080/account-holder/set-balance
    /*
    {
        "id": 2,
        "balance": 10000.20
    }
    */
    // Método para actualizar el balance de una cuenta mediante su ID (Solo pueden los Admins)
    @PatchMapping("/set-balance")
    @ResponseStatus(HttpStatus.OK)
    public Account setBalance(@RequestBody AccountDTO accountDTO) {
        return accountService.setBalance(accountDTO);
    }

    /*

     El usuario debe proporcionar el nombre del propietario principal o secundario y la identificación de la cuenta que
     debe recibir la transferencia.

     {
        "namePrimaryOwner": "Raul",
        "nameSecondaryOwner": null,
        "idRecipientAccount: 1,
        "balance": 500
     }

    */

    //Método para transferir dinero de una cuenta a otra cuenta
    @PostMapping("/send-money/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sendMoney(@PathVariable Long id, @RequestBody Transfer transfer) {
        accountService.sendMoney(id, transfer);
    }

}
