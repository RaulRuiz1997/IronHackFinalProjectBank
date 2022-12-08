package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.CheckingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checking")
public class CheckingController {

    // todo: los test de los controller tienen que ser con MockMvc

    @Autowired
    CheckingService checkingService;

    // GET -> localhost:8080/checking/all
    // Método para encontrar todos los Checking Account
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> findAll() {
        return checkingService.findAll();
    }

    // GET -> localhost:8080/checking/id/3
    // Método para encontrar un Checking Account por ID con PathVariable
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findByIdPathVariable(@PathVariable Long id) {
        return checkingService.findById(id);
    }

    // GET -> localhost:8080/checking/id?id=3
    // Método para encontrar un Checking Account por ID con RequestParam
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Checking findByIdRequestParam(@RequestParam Long id) {
        return checkingService.findById(id);
    }

    // POST -> localhost:8080/checking/add
    /*
    {
        "balance": 500.0,
        "primaryOwner": {
            "id": 3,
            "name": "Phillip (C.A.)",
            "dateOfBirth": "1982-02-14",
            "primaryAddress": {
                "name": "Grove Street",
                "numberHouse": 24,
                "city": "LOS SANTOS",
                "zipCode": 11923
            }
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
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid Checking checkingAccount) {
        return checkingService.createCheckingAccount(checkingAccount);
    }

    // PUT -> localhost:8080/checking/update-put
    /*
    {
        "id": 3,
        "balance": 500.0,
        "primaryOwner": {
            "id": 3,
            "name": "Phillip (C.A.)",
            "dateOfBirth": "1982-02-14",
            "primaryAddress": {
                "name": "Grove Street",
                "numberHouse": 24,
                "city": "LOS SANTOS",
                "zipCode": 11923
            }
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
    // Método para actualizar todos los campos de un Checking Account
    @PutMapping("/update-put")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking updateCheckingAccountPut(@RequestBody @Valid Checking checkingAccount) {
        return checkingService.updateCheckingAccountPut(checkingAccount);
    }

    // PATCH -> localhost:8080/checking/update-patch
    /*
    {
        "id": 3,
        "balance": 500.0,
        "primaryOwner": {
            "id": 3,
            "name": "Jose (C.A.)",
            "dateOfBirth": "1982-02-14",
            "primaryAddress": {
                "name": "Grove Street",
                "numberHouse": 24,
                "city": "LOS SANTOS",
                "zipCode": 11923
            }
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
    // Método para actualizar ciertos campos de un Checking Account
    @PatchMapping("/update-patch")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking updateCheckingAccountPatch(@RequestBody @Valid Checking checkingAccount) {
        return checkingService.updateCheckingAccountPatch(checkingAccount);
    }

    // DELETE -> localhost:8080/checking/delete/3
    // Método para eliminar un Checking Account mediante su ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCheckingAccount(@PathVariable Long id) {
        checkingService.deleteCheckingAccountById(id);
    }

}
