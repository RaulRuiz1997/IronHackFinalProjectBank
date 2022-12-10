package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.AccountDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.TransactionDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos.TransactionThirdPartyUsersDTO;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.StudentChecking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.roles.Role;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transactions.Transaction;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.ThirdParty;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.AccountRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.StudentCheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.transactions.TransactionRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.ThirdPartyRepository;
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

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    SavingsRepository savingsRepository;

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

        // No valído si son números negativos por si los admins tienen que hacer pruebas, etc

        Account account;

        if (accountRepository.findById(accountDTO.getId()).isPresent()) {

            account = accountRepository.findById(accountDTO.getId()).get();

            // Cambiamos el balance que nos llega por parámetro
            account.setBalance(accountDTO.getBalance());

            return accountRepository.save(account);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrada");

    }


    public TransactionDTO sendMoney(Long idSenderAccount, Transaction transaction, UserDetails userDetails) {

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

    public TransactionThirdPartyUsersDTO moveMoneyFromThirdPartyUser(String hashedKey, TransactionThirdPartyUsersDTO transaction, UserDetails userDetails) {

        boolean isAdmin = false;
        boolean isThirdPartyUser = false;
        ThirdParty thirdPartyUser;
        Checking checkingAccount;
        StudentChecking studentCheckingAccount;
        Savings savingsAccount;

        // Obtenemos el usuario
        User user = userRepository.findByName(userDetails.getUsername()).get();

        // Comprobamos los roles que tiene ese usuario
        for (Role rol : user.getRoles()) {

            if (rol.getRole().equals("ADMIN")) isAdmin = true;
            if (rol.getRole().equals("THIRD-PARTY-USER")) isThirdPartyUser = true;

        }

        // Verificamos que el Rol sea o ADMIN o THIRD-PARTY-USER
        if (isAdmin || isThirdPartyUser) {

            if (thirdPartyRepository.findById(user.getId()).isPresent()) {

                // Obtenemos el Third Party User
                thirdPartyUser = thirdPartyRepository.findById(user.getId()).get();

                // Verificamos que la hashed key proporcionada sea la misma que la que tiene el usuario
                if (thirdPartyUser.getHashedKey().equals(hashedKey)) {

                    // Verificamos que el ID de la cuenta que recibimos exista
                    // (Debe ser una cuenta Checking o StudentChecking o Savings)
                    if (checkingRepository.findById(transaction.getAccountId()).isPresent()) {

                        checkingAccount = checkingRepository.findById(transaction.getAccountId()).get();

                        // Verificamos que la secret key proporcionada sea la misma que la que tengamos en la cuenta
                        if (checkingAccount.getSecretKey().equals(transaction.getSecretKey())) {

                            // Si el valor "amount" que le hemos pasado es positivo le sumamos ese amount al balance de la cuenta
                            // Si es negativo, se lo restamos
                            checkingAccount.setBalance(checkingAccount.getBalance().add(transaction.getAmount()));

                            checkingRepository.save(checkingAccount);

                            return new TransactionThirdPartyUsersDTO(transaction.getAmount(), transaction.getAccountId(), checkingAccount.getBalance());

                        } else {

                            throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "La secret key no es correcta");

                        }

                    } else if (studentCheckingRepository.findById(transaction.getAccountId()).isPresent()) {

                        studentCheckingAccount = studentCheckingRepository.findById(transaction.getAccountId()).get();

                        // Verificamos que la secret key proporcionada sea la misma que la que tengamos en la cuenta
                        if (studentCheckingAccount.getSecretKey().equals(transaction.getSecretKey())) {

                            // Si el valor "amount" que le hemos pasado es positivo le sumamos ese amount al balance de la cuenta
                            // Si es negativo, se lo restamos
                            studentCheckingAccount.setBalance(studentCheckingAccount.getBalance().add(transaction.getAmount()));

                            studentCheckingRepository.save(studentCheckingAccount);

                            return new TransactionThirdPartyUsersDTO(transaction.getAmount(), transaction.getAccountId(), studentCheckingAccount.getBalance());


                        } else {

                            throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "La secret key no es correcta");

                        }

                    } else if (savingsRepository.findById(transaction.getAccountId()).isPresent()) {

                        savingsAccount = savingsRepository.findById(transaction.getAccountId()).get();

                        // Verificamos que la secret key proporcionada sea la misma que la que tengamos en la cuenta
                        if (savingsAccount.getSecretKey().equals(transaction.getSecretKey())) {

                            // Si el valor "amount" que le hemos pasado es positivo le sumamos ese amount al balance de la cuenta
                            // Si es negativo, se lo restamos
                            savingsAccount.setBalance(savingsAccount.getBalance().subtract(transaction.getAmount()));

                            savingsRepository.save(savingsAccount);

                            return new TransactionThirdPartyUsersDTO(transaction.getAmount(), transaction.getAccountId(), savingsAccount.getBalance());


                        } else {

                            throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "La secret key no es correcta");

                        }

                    } else {

                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrada");

                    }

                }

            }


        }

        return null;

    }

}
