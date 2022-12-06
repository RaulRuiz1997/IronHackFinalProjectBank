package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.*;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    // Método para devolver todas las Saving Accounts de la BBDD
    public List<Savings> findAll() {

        return savingsRepository.findAll();

    }

    // Método para devolver una Saving Account por ID de la BBDD
    public Savings findById(Long id) {

        if (savingsRepository.findById(id).isPresent()) {

            return savingsRepository.findById(id).get();

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

        }

    }

    // Método para crear una Saving Account
    public Savings createSavingAccount(Savings savingAccount) {

        return savingsRepository.save(savingAccount);

    }

    // Método para actualizar una Saving Account mediante PUT REQUEST
    public Savings updateSavingAccountPut(Savings savingAccount) {

        if (savingsRepository.findById(savingAccount.getId()).isPresent()) {

            return savingsRepository.save(savingAccount);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para actualizar una Saving Account mediante PATCH REQUEST
    public Savings updateSavingAccountPatch(Savings savingAccount) {

        Savings savingAccountUpdated;

        if (savingsRepository.findById(savingAccount.getId()).isPresent()) {

            savingAccountUpdated = savingsRepository.findById(savingAccount.getId()).get();

            // Comprobamos los atributos que no sean null para actualizarlos, los demás no los actualizamos
            if (savingAccount.getBalance() != null) {
                savingAccountUpdated.setBalance(savingAccount.getBalance());
            }
            if (savingAccount.getPrimaryOwner() != null) {
                savingAccountUpdated.setPrimaryOwner(savingAccount.getPrimaryOwner());
            }
            if (savingAccount.getSecondaryOwner() != null) {
                savingAccountUpdated.setSecondaryOwner(savingAccount.getSecondaryOwner());
            }
            if (savingAccount.getStatus() != null) {
                savingAccountUpdated.setStatus(savingAccount.getStatus());
            }
            if (savingAccount.getSecretKey() != null) {
                savingAccountUpdated.setSecretKey(savingAccount.getSecretKey());
            }

            return savingsRepository.save(savingAccountUpdated);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para borrar una Saving Account por ID de la BBDD
    public void deleteSavingAccountById(Long id) {

        savingsRepository.deleteById(id);

    }

}
