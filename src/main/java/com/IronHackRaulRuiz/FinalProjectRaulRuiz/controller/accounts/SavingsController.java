package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.SavingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/savings")
public class SavingsController {

    @Autowired
    SavingsService savingsService;

    // GET -> localhost:8080/savings/all
    // Método para encontrar todos los Savings Account
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> findAll() {
        return savingsService.findAll();
    }

    // GET -> localhost:8080/savings/id/1
    // Método para encontrar un Saving Account por ID con PathVariable
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings findByIdPathVariable(@PathVariable Long id) {
        return savingsService.findById(id);
    }

    // GET -> localhost:8080/savings/id?id=1
    // Método para encontrar un Saving Account por ID con RequestParam
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Savings findByIdRequestParam(@RequestParam Long id) {
        return savingsService.findById(id);
    }

    // POST -> localhost:8080/savings/add
    /*
    {
        "balance": 444444.24,
        "primaryOwner": {
            "id": 1,
            "name": "Peter (S.A.)",
            "dateOfBirth": "1997-12-19",
            "primaryAddress": {
                "name": "C/ Falsa",
                "numberHouse": 123,
                "city": "BCN",
                "zipCode": 8100
            }
        },
        "secondaryOwner": {
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
        "creationDate": "2022-12-06",
        "status": "FROZEN",
        "minimumBalance": 111.0,
        "secretKey": "aaaaaaaaaaaaa",
        "interestRate": 0.1,
        "penalty_FEE": 30.0
    }
    */
    // Método para añadir un Saving Account
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingAccount(@RequestBody @Valid Savings savingAccount) {
        return savingsService.createSavingAccount(savingAccount);
    }

    // PUT -> localhost:8080/saving/update-put
    /*
    {
        "id" : 1,
        "balance": 234643.24,
        "primaryOwner": {
            "id": 1,
            "name": "Peter (S.A.)",
            "dateOfBirth": "1997-12-19",
            "primaryAddress": {
                "name": "C/ Falsa",
                "numberHouse": 123,
                "city": "BCN",
                "zipCode": 8100
            }
        },
        "secondaryOwner": {
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
        "creationDate": "2022-12-06",
        "status": "FROZEN",
        "minimumBalance": 124.0,
        "secretKey": "12345678",
        "interestRate": 0.2,
        "penalty_FEE": 30.0
    }
    */
    // Método para actualizar todos los campos de un Saving Account
    @PutMapping("/update-put")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Savings updateSavingAccountPut(@RequestBody @Valid Savings savingAccount) {
        return savingsService.updateSavingAccountPut(savingAccount);
    }

    // PATCH -> localhost:8080/savings/update-patch
    /*
    {
        "id" : 1,
        "balance": 111111.24,
        "primaryOwner": {
            "id": 1,
            "name": "Peter (S.A.)",
            "dateOfBirth": "1997-12-19",
            "primaryAddress": {
                "name": "C/ Falsa",
                "numberHouse": 123,
                "city": "BCN",
                "zipCode": 8100
            }
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-06",
        "status": "FROZEN",
        "minimumBalance": 999.0,
        "secretKey": "99999",
        "interestRate": 0.2,
        "penalty_FEE": 30.0
    }
    */
    // Método para actualizar ciertos campos de un Saving Account
    @PatchMapping("/update-patch")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Savings updateSavingAccountPatch(@RequestBody @Valid Savings savingAccount) {
        return savingsService.updateSavingAccountPatch(savingAccount);
    }

    // DELETE -> localhost:8080/savings/delete/4
    // Método para eliminar un Saving Account mediante su ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSavingAccount(@PathVariable Long id) {
        savingsService.deleteSavingAccountById(id);
    }

}
