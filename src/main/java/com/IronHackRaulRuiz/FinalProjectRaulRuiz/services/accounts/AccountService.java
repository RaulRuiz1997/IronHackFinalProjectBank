package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.AccountDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.TransactionDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.roles.Role;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transfers.Transaction;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.AccountRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.transactions.TransactionRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
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

    @Autowired
    TransactionRepository transactionRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public BigDecimal getBalance(Long idAccount, UserDetails userDetails) {

        Account account;
        String nameUser;
        boolean isAdmin = false;

        // Obtenemos el usuario para saber si es ADMIN o no
        User user = userRepository.findByName(userDetails.getUsername()).get();

        // Comprobamos los roles que tiene ese usuario
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) isAdmin = true;
        }

        // Obtenemos el nombre del usuario
        nameUser = userDetails.getUsername();

        if (accountRepository.findById(idAccount).isPresent()) {

            account = accountRepository.findById(idAccount).get();

            // Comprobamos que el Secondary Owner no sea null
            if (account.getSecondaryOwner() != null) {

                // Comprobamos que el usuario que accede a la cuenta es: Primary Owner o Secondary Owner o Admin
                if (nameUser.equals(account.getPrimaryOwner().getName())
                        || nameUser.equals(account.getSecondaryOwner().getName())
                        || isAdmin) {

                    return account.getBalance();

                } else {

                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario no permitido");

                }

            } else {

                // Comprobamos que el usuario que accede a la cuenta es: Primary Owner o Admin
                if (nameUser.equals(account.getPrimaryOwner().getName())
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


    public TransactionDTO sendMoney(Long idSenderAccount, Transaction transaction, UserDetails userDetails) throws Exception {

        // verificar que el userDetails el nombre sea el primary owner name o secondary owner name o admin

        Account senderAccount;
        Account recipientAccount;
        Transaction newTransaction;

        String nameUser;
        boolean isAdmin = false;

        // Obtenemos el usuario para saber si es ADMIN o no
        User user = userRepository.findByName(userDetails.getUsername()).get();

        // Comprobamos los roles que tiene ese usuario
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("ADMIN")) isAdmin = true;
        }

        // Obtenemos el nombre del usuario
        nameUser = userDetails.getUsername();

        // Comprobamos que los IDs de las cuentas emisoras y remitente existan
        if (accountRepository.findById(idSenderAccount).isPresent()
                && accountRepository.findById(transaction.getIdRecipientAccount()).isPresent()) {

            // Guardamos la cuenta emisora
            senderAccount = accountRepository.findById(idSenderAccount).get();

            // Guardamos la cuenta remitente
            recipientAccount = accountRepository.findById(transaction.getIdRecipientAccount()).get();

            // Comprobamos que el usuario que accede a la cuenta es: Primary Owner o Secondary Owner o Admin
            if (senderAccount.getPrimaryOwner().getName().equals(nameUser)
                    || senderAccount.getSecondaryOwner().getName().equals(nameUser)
                    || isAdmin) {

                // Si tenemos fondos suficientes ejecutamos la transferencia
                if (senderAccount.getBalance().compareTo(transaction.getBalance()) >= 0) {

                    // Restamos el balance a la cuenta remitente (Restamos la cantidad que tenemos + el dinero de la transferencia)
                    senderAccount.setBalance(senderAccount.getBalance().subtract(transaction.getBalance()));

                    // Sumamos el balance a la cuenta destinatario (Sumamos la cantidad que tenemos + el dinero de la transferencia)
                    recipientAccount.setBalance(recipientAccount.getBalance().add(transaction.getBalance()));

                    // Guardamos en la BBDD las cuentas con sus balance actualizados
                    accountRepository.save(senderAccount);

                    accountRepository.save(recipientAccount);

                    newTransaction = new Transaction(idSenderAccount, transaction.getIdRecipientAccount(), transaction.getNameRecipientOwner(), transaction.getBalance());

                    return new TransactionDTO(transactionRepository.save(newTransaction).getId(), transaction.getBalance(), senderAccount.getBalance());

                } else {

                    throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay fondos suficientes");

                }

            } else {

                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario no permitido");

            }


        } else {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID no encontrado");

        }

    }

}
