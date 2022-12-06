package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.StudentChecking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    // Método para devolver todas las Checking Accounts de la BBDD
    public List<Checking> findAll() {

        return checkingRepository.findAll();

    }

    // Método para devolver una Checking Accounts por ID de la BBDD
    public Checking findById(Long id) {

        if (checkingRepository.findById(id).isPresent()) {

            return checkingRepository.findById(id).get();

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

        }

    }

    // Método para crear una Checking Account o Student Account
    public Account createCheckingAccount(Checking checkingAccount) {

        StudentChecking studentChecking;

        // Guardamos el periodo para obtener la edad del primary owner
        Period period = Period.between(checkingAccount.getPrimaryOwner().getDateOfBirth(), LocalDate.now());

        // Si el primaryOwner es menor a 24 años, creamos una Student Checking Account, si no, creamos una Checking Account
        if (period.getYears() < 24) {

            studentChecking = new StudentChecking(checkingAccount.getBalance(), checkingAccount.getPrimaryOwner(), checkingAccount.getSecondaryOwner(),
                    checkingAccount.getStatus(), checkingAccount.getSecretKey());

            return studentCheckingRepository.save(studentChecking);

        } else {

            return checkingRepository.save(checkingAccount);

        }

    }

    // Método para actualizar una Checking Account mediante PUT REQUEST
    public Checking updateCheckingAccountPut(Checking checkingAccount) {

        if (checkingRepository.findById(checkingAccount.getId()).isPresent()) {

            return checkingRepository.save(checkingAccount);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para actualizar una Checking Account mediante PATCH REQUEST
    public Checking updateCheckingAccountPatch(Checking checkingAccount) {

        Checking checkingAccountUpdated;

        if (checkingRepository.findById(checkingAccount.getId()).isPresent()) {

            checkingAccountUpdated = checkingRepository.findById(checkingAccount.getId()).get();

            // Comprobamos los atributos que no sean null para actualizarlos, los demás no los actualizamos
            if (checkingAccount.getBalance() != null) {
                checkingAccountUpdated.setBalance(checkingAccount.getBalance());
            }
            if (checkingAccount.getPrimaryOwner() != null) {
                checkingAccountUpdated.setPrimaryOwner(checkingAccount.getPrimaryOwner());
            }
            if (checkingAccount.getSecondaryOwner() != null) {
                checkingAccountUpdated.setSecondaryOwner(checkingAccount.getSecondaryOwner());
            }
            if (checkingAccount.getStatus() != null) {
                checkingAccountUpdated.setStatus(checkingAccount.getStatus());
            }
            if (checkingAccount.getSecretKey() != null) {
                checkingAccountUpdated.setSecretKey(checkingAccount.getSecretKey());
            }

            return checkingRepository.save(checkingAccountUpdated);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    // Método para borrar una Checking Account por ID de la BBDD
    public void deleteCheckingAccountById(Long id) {

        // En el método findById(id) ya comprobamos si la ID existe o no
        Checking checkingAccount = findById(id);

        checkingRepository.delete(checkingAccount);

    }

}
