package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checking")
public class CheckingController {

    @Autowired
    CheckingService checkingService;

    // GET -> localhost:8080/checking/all
    // Método para encontrar todos los Checking Account
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> findAll() {
        return checkingService.findAll();
    }

    // GET -> localhost:8080/checking/id/4
    // Método para encontrar un Checking Account por id con PathVariable
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findByIdPathVariable(@PathVariable Long id) {
        return checkingService.findById(id);
    }

    // GET -> localhost:8080/checking/id?id=4
    // Método para encontrar un Checking Account por id con RequestParam
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Checking findByIdRequestParam(@RequestParam Long id) {
        return checkingService.findById(id);
    }

    // POST ->
    /*
    {
        "id": 4,
        "balance": 333.0,
        "primaryOwner": {
            "id": 1,
            "name": "Raul",
            "dateOfBirth": "1997-12-19",
            "primaryAddress": {
                "name": "C/ Falsa",
                "numberHouse": 123,
                "city": "BCN",
                "zipCode": 8100
            }
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-05",
        "status": "ACTIVE",
        "monthlyMaintenanceFee": 2,
        "minimumBalance": 0.2,
        "secretKey": "Secret Key",
        "penalty_FEE": 40.0
    }
    */
    // Método para añadir un Checking Account
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createCheckingAccount(@RequestBody Checking checkingAccount) {
        return checkingService.createCheckingAccount(checkingAccount);
    }

    // PUT -> localhost:8080/checking/update
    /*
    {
        "id": 3,
        "balance": 1250.0,
        "primaryOwner": {
            "id": 1,
            "name": "RAUL",
            "dateOfBirth": "1997-12-19",
            "primaryAddress": {
                "name": "C/ FALSISIMA",
                "numberHouse": 456,
                "city": "BCN",
                "zipCode": 8100
            }
        },
        "secondaryOwner": null,
        "creationDate": "2022-12-05",
        "status": "ACTIVE",
        "monthlyMaintenanceFee": 6,
        "minimumBalance": 0.04,
        "secretKey": "Secret Key2222",
        "penalty_FEE": 40.0
    }
    */
    // Método para actualizar ciertos campos de un Checking Account
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking updateCheckingAccount(@RequestBody Checking checkingAccount) {
        return checkingService.updateCheckingAccount(checkingAccount);
    }

    // todo: falta un método DELETE

}
