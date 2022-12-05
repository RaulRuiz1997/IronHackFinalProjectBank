package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    // todo: OJO, ESTOS METODOS SON LOGICA DEL SERVICE

    public Admin() {
    }

    public Admin(String name) {
        super(name);
    }

    // todo: Mirar esto porque le tengo que pasar por argumentos todos los parámetros del constructor como hice pasándole el accountHolder, no?
    public Savings createSavingAccount(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Double minimumBalance, String secretKey,
                                       Double interestRate) {

        Savings savingAccount = new Savings(balance, primaryOwner, secondaryOwner, status, minimumBalance, secretKey, interestRate);

        // todo: devolver el saving account de la BBDD, esta logica va en el service, no aqui

        return savingAccount;

    }

    public CreditCard createCreditCardAccount(AccountHolder primaryOwner) {

        CreditCard creditCardAccount = new CreditCard(500.0, primaryOwner, null, StatusAccount.ACTIVE, 1000, 0.1);

        return creditCardAccount;

    }

    public Checking createCheckingAccount(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Integer monthlyMaintenanceFee, Double minimumBalance, String secretKey) {

        Checking checkingAccount = new Checking(500.0, primaryOwner, null, StatusAccount.ACTIVE, 2, 0.2, "Secret Key");

        return checkingAccount;

    }

}
