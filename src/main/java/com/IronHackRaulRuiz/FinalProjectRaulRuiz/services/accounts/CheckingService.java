package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    public List<Checking> findAll() {
        return checkingRepository.findAll();
    }

    public Checking findById(Long id) {

        if (checkingRepository.findById(id).isPresent()) {

            return checkingRepository.findById(id).get();

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

        }

    }

    public Checking createCheckingAccount(Checking checkingAccount) {

        return checkingRepository.save(checkingAccount);

    }

    // todo: preguntar a los profes si el update es así
    public Checking updateCheckingAccount(Checking checkingAccount) {

        Checking checkingAccountUpdated;

        if (checkingRepository.findById(checkingAccount.getId()).isPresent()) {

            checkingAccountUpdated = checkingRepository.findById(checkingAccount.getId()).get();

            checkingAccountUpdated.setBalance(checkingAccount.getBalance());
            checkingAccountUpdated.setPrimaryOwner(checkingAccount.getPrimaryOwner());
            checkingAccountUpdated.setSecondaryOwner(checkingAccount.getSecondaryOwner());
            checkingAccountUpdated.setStatus(checkingAccount.getStatus());
            checkingAccountUpdated.setMonthlyMaintenanceFee(checkingAccount.getMonthlyMaintenanceFee());
            checkingAccountUpdated.setMinimumBalance(checkingAccount.getMinimumBalance());
            checkingAccountUpdated.setSecretKey(checkingAccount.getSecretKey());

            return checkingRepository.save(checkingAccountUpdated);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

}
