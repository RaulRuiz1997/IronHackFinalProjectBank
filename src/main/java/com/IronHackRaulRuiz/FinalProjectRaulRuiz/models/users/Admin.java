package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name, String password) {
        super(name, password);
    }

    /*public Savings createSavingAccount(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, BigDecimal minimumBalance, String secretKey,
                                       BigDecimal interestRate) {

        Savings savingAccount = new Savings(balance, primaryOwner, secondaryOwner, status, minimumBalance, secretKey, interestRate);

        return savingAccount;

    }

    public CreditCard createCreditCardAccount(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Integer creditLimit, BigDecimal interestRate) {

        CreditCard creditCardAccount = new CreditCard(balance, primaryOwner, secondaryOwner, status, creditLimit, interestRate);

        return creditCardAccount;

    }

    public Checking createCheckingAccount(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Integer monthlyMaintenanceFee, BigDecimal minimumBalance, String secretKey) {

        Checking checkingAccount = new Checking(new BigDecimal("500.0"), primaryOwner, null, StatusAccount.ACTIVE, "Secret Key");

        return checkingAccount;

    }*/

}
