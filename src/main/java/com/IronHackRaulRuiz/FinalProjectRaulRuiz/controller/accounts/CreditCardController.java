package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CreditCardService;
import jakarta.validation.Valid;
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

    // POST -> localhost:8080/credit-card/add
    /*
    {
        "balance": 111111.9,
        "primaryOwner": {
            "id": 2,
            "name": "John (C.C.A)",
            "dateOfBirth": "1968-06-25",
            "primaryAddress": {
                "name": "Street Oporto",
                "numberHouse": 45,
                "city": "SANT FRANCISCO",
                "zipCode": 449982
            }
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-06",
        "status": "ACTIVE",
        "creditLimit": 111111,
        "interestRate": 0.1,
        "min_CREDIT_LIMIT": 100,
        "max_CREDIT_LIMIT": 100000,
        "max_INTEREST_RATE": 0.2,
        "min_INTEREST_RATE": 0.1,
        "penalty_FEE": 40.0
    }
    */
    // Método para añadir un Credit Card Account
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCard creditCard) {

        return creditCardService.createCreditCardAccount(creditCard);

    }

    // PUT -> localhost:8080/credit-card/update-put
    /*
    {
        "id": 2,
        "balance": 12345.2,
        "primaryOwner": {
            "id": 2,
            "name": "John (C.C.A)",
            "dateOfBirth": "1968-06-25",
            "primaryAddress": {
                "name": "Street Oporto",
                "numberHouse": 45,
                "city": "SANT FRANCISCO",
                "zipCode": 449982
            }
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-06",
        "status": "ACTIVE",
        "creditLimit": 888,
        "interestRate": 0.1,
        "max_CREDIT_LIMIT": 888,
        "max_INTEREST_RATE": 0.2,
        "min_CREDIT_LIMIT": 100,
        "min_INTEREST_RATE": 0.1,
        "penalty_FEE": 40.0
    }
    */
    // Método para actualizar todos los campos de una Credit Card Account
    @PutMapping("/update-put")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard updateCreditCardAccountPut(@RequestBody @Valid CreditCard creditCardAccount) {

        return creditCardService.updateCreditCardAccountPut(creditCardAccount);

    }

    // PATCH -> localhost:8080/credit-card/update-patch
    /*
    {
        "id": 2,
        "balance": 12345.2,
        "primaryOwner": {
            "id": 2,
            "name": "John (C.C.A)",
            "dateOfBirth": "1968-06-25",
            "primaryAddress": {
                "name": "Street Oporto",
                "numberHouse": 45,
                "city": "SANT FRANCISCO",
                "zipCode": 449982
            }
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-06",
        "status": "ACTIVE",
        "creditLimit": 999,
        "interestRate": 0.1,
        "max_CREDIT_LIMIT": 888,
        "max_INTEREST_RATE": 0.2,
        "min_CREDIT_LIMIT": 100,
        "min_INTEREST_RATE": 0.1,
        "penalty_FEE": 40.0
    }
    */
    // Método para actualizar ciertos los campos de una Credit Card Account
    @PatchMapping("/update-patch")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard updateCreditCardAccountPatch(@RequestBody @Valid CreditCard creditCardAccount) {

        return creditCardService.updateCreditCardAccountPatch(creditCardAccount);

    }

    // DELETE -> localhost:8080/credit-card/delete/2
    // Método para eliminar un Credit Card Account mediante su ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deleteCreditCardAccountById(@PathVariable Long id) {

        creditCardService.delete(id);

    }

}
