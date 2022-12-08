package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.users.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    // POST -> localhost:8080/admin/add-saving
    /*
    {
        "balance": 444444.24,
        "primaryOwner": {
            "id": 2,
            "name": "Jaume",
            "dateOfBirth": "1985-05-15",
            "primaryAddress": {
                "name": "Avenida Edimburgo",
                "numberHouse": 12,
                "city": "Edimburgo",
                "zipCode": 99456
            },
            "mailingAddress": null
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
    @PostMapping("/add-saving")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingAccount(@RequestBody @Valid Savings savingsAccount) {
        return adminService.createSavingAccount(savingsAccount);
    }

    // POST -> localhost:8080/admin/add-credit
    /*
    {
        "balance": 111111.9,
        "primaryOwner": {
            "id": 2,
            "name": "Jaume",
            "dateOfBirth": "1985-05-15",
            "primaryAddress": {
                "name": "Avenida Edimburgo",
                "numberHouse": 12,
                "city": "Edimburgo",
                "zipCode": 99456
            },
            "mailingAddress": null
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
    @PostMapping("/add-credit")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCard creditCardAccount) {
        return adminService.createCreditCardAccount(creditCardAccount);
    }

    // POST -> localhost:8080/admin/add-checking
    /*
    {
        "balance": 500.0,
        "primaryOwner": {
            "id": 2,
            "name": "Jaume",
            "dateOfBirth": "1985-05-15",
            "primaryAddress": {
                "name": "Avenida Edimburgo",
                "numberHouse": 12,
                "city": "Edimburgo",
                "zipCode": 99456
            },
            "mailingAddress": null
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-06",
        "status": "ACTIVE",
        "secretKey": "Secret Key",
        "minimumBalance": 250.0,
        "monthlyMaintenanceFee": 12,
        "penalty_FEE": 40.0
    }
    */
    // Método para añadir un Checking Account
    @PostMapping("/add-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid Checking checkingAccount) {
        return adminService.createCheckingAccount(checkingAccount);
    }

}
