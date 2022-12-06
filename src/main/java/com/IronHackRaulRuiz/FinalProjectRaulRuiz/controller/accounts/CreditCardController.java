package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    // GET -> localhost:8080/credit-card/all
    // Método para encontrar todos los Credit Card Accounts
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditCard> findAll() {
        return creditCardService.findAll();
    }

    // GET -> localhost:8080/credit-card/id/2
    // Método para encontrar un Credit Card Account por ID con PathVariable
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CreditCard findByIdPathVariable(@PathVariable Long id) {
        return creditCardService.findById(id);
    }

    // GET -> localhost:8080/credit-card/id?id=2
    // Método para encontrar un Credit Card Account por ID con RequestParam
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.FOUND)
    public CreditCard findByIdRequestParam(@RequestParam Long id) {
        return creditCardService.findById(id);
    }



}
