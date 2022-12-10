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

}
