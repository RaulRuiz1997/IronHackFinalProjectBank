package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    // Método para devolver todas las Credit Card Accounts de la BBDD
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    // Método para devolver una Credit Card Account por ID de la BBDD
    public CreditCard findById(Long id) {

        if (creditCardRepository.findById(id).isPresent()) {

            return creditCardRepository.findById(id).get();

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para crear una Credit Card Account
    public CreditCard createCreditCardAccount(CreditCard creditCard) {

        return creditCardRepository.save(creditCard);

    }

    // Método para actualizar una Credit Card Account mediante PUT REQUEST
    public CreditCard updateCreditCardAccountPut(CreditCard creditCardAccount) {

        if (creditCardRepository.findById(creditCardAccount.getId()).isPresent()) {

            return creditCardRepository.save(creditCardAccount);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para actualizar una Credit Card Account mediante PATCH REQUEST
    public CreditCard updateCreditCardAccountPatch(CreditCard creditCardAccount) {

        CreditCard creditCardUpdated;

        if (creditCardRepository.findById(creditCardAccount.getId()).isPresent()) {

            creditCardUpdated = creditCardRepository.findById(creditCardAccount.getId()).get();

            // Comprobamos los atributos que no sean null para actualizarlos, los demás no los actualizamos
            if (creditCardAccount.getBalance() != null) {
                creditCardUpdated.setBalance(creditCardAccount.getBalance());
            }
            if (creditCardAccount.getPrimaryOwner() != null) {
                creditCardUpdated.setPrimaryOwner(creditCardAccount.getPrimaryOwner());
            }
            if (creditCardAccount.getSecondaryOwner() != null) {
                creditCardUpdated.setSecondaryOwner(creditCardAccount.getSecondaryOwner());
            }
            if (creditCardAccount.getStatus() != null) {
                creditCardUpdated.setStatus(creditCardAccount.getStatus());
            }
            if (creditCardAccount.getCreditLimit() != null) {
                creditCardUpdated.setCreditLimit(creditCardAccount.getCreditLimit());
            }
            if (creditCardAccount.getInterestRate() != null) {
                creditCardUpdated.setInterestRate(creditCardAccount.getInterestRate());
            }

            return creditCardRepository.save(creditCardUpdated);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para borrar una Credit Card Account por ID de la BBDD
    public void delete(Long id) {

        creditCardRepository.deleteById(id);

    }

}
