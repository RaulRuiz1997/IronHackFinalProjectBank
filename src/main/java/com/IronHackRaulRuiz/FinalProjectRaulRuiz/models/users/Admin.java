package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    public Admin(String name) {
        super(name);
    }

    // todo: mirar esto porque se guarda bien pero el metodo tendria que recibir la Address, un AccountHolder y los atributos para guardar un SavingAccount
    public Savings createSavingAccount() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        User accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Savings savingAccount = new Savings(500.0, accountHolderRaul, null, 0.0, 200.0, "secretKey", LocalDate.of(2015, 5, 5),
                StatusAccount.ACTIVE, 0.010);

        return savingAccount;

    }

    public CreditCard createCreditCardAccount() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        User accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        CreditCard creditCard = new CreditCard(500.0, accountHolderRaul, null, 0.0, 100, 0.2);

        return creditCard;

    }

    public Checking createCheckingAccount() {

        Address address = new Address("C/ Falsa", 123, "BCN", 8100);

        User accountHolderRaul = new AccountHolder("Raul", LocalDate.of(1997, 12, 19), address);

        Checking checking = new Checking(500.0, accountHolderRaul, null, 0.0, 100, 0.2, "Secret Key", LocalDate.of(2000, 5, 16), StatusAccount.ACTIVE);

        return checking;

    }

}
