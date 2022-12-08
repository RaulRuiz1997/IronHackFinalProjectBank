package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.AccountDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.roles.Role;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transfers.Transfer;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.AccountRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public BigDecimal getBalance(Long idAccount, UserDetails userDetails) {

        Account account;
        String name;
        boolean isAdmin = false;

        // Obtenemos el usuario para saber si es ADMIN o no
        User user = userRepository.findByName(userDetails.getUsername()).get();

        // Comprobamos los roles que tiene ese usuario
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) isAdmin = true;
        }

        // Obtenemos el nombre del usuario
        name = userDetails.getUsername();

        if (accountRepository.findById(idAccount).isPresent()) {

            account = accountRepository.findById(idAccount).get();

            // Comprobamos que el Secondary Owner no sea null
            if (account.getSecondaryOwner() != null) {

                // Comprobamos que el usuario que accede a la cuenta es: Primary Owner o Secondary Owner o Admin
                if (name.equals(account.getPrimaryOwner().getName())
                        || name.equals(account.getSecondaryOwner().getName())
                        || isAdmin) {

                    return account.getBalance();

                } else {

                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario no permitido");

                }

            } else {

                // Comprobamos que el usuario que accede a la cuenta es: Primary Owner o Admin
                if (name.equals(account.getPrimaryOwner().getName())
                        || isAdmin) {

                    return account.getBalance();

                } else {

                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario no permitido");

                }

            }

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrada");

    }

    public Account setBalance(AccountDTO accountDTO) {

        Account account;

        if (accountRepository.findById(accountDTO.getId()).isPresent()) {

            account = accountRepository.findById(accountDTO.getId()).get();

            // Cambiamos el balance que nos llega por parámetro
            account.setBalance(accountDTO.getBalance());

            return accountRepository.save(account);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrada");

    }

    public void sendMoney(Long idSenderAccount, Transfer transfer) {

        Account account;

        if (accountRepository.findById(idSenderAccount).isPresent()) {

            account = accountRepository.findById(idSenderAccount).get();

            if (transfer.getNamePrimaryOwner() != null) {

                // Si tenemos fondos suficientes ejecutamos la transferencia
                if (account.getBalance().compareTo(transfer.getBalance()) >= 0) {

                    // Restamos el balance a la cuenta remitente

                    // Sumamos el balance a la cuenta destinatario


                } else {

                    throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay fondos suficientes");

                }

                return;

            }

            if (transfer.getNameSecondaryOwner() != null) {



                return;

            }

        }

    }

}
