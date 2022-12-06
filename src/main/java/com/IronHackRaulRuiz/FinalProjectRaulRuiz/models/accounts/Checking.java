package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    @NotNull
    private final Integer MONTHLY_MAINTENANCE_FEE = 12;
    @NotNull
    private final Double MINIMUM_BALANCE = 250.0;
    @NotNull
    private String secretKey;

    public Checking() {
    }

    public Checking(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, status);
        this.secretKey = secretKey;
    }

    public Integer getMonthlyMaintenanceFee() {
        return MONTHLY_MAINTENANCE_FEE;
    }


    public Double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {

        return "Checking{" +
                "id=" + getId() +
                ", balance=" + getBalance() +
                ", primaryOwner=" + getPrimaryOwner() +
                ", secondaryOwner=" + getSecondaryOwner() +
                ", PENALTY_FEE=" + getPENALTY_FEE() +
                ", creationDate=" + getCreationDate() +
                ", status=" + getStatus() +
                ", MONTHLY_MAINTENANCE_FEE=" + MONTHLY_MAINTENANCE_FEE +
                ", MINIMUM_BALANCE=" + MINIMUM_BALANCE +
                ", secretKey='" + secretKey + '\'' +
                '}';

    }

}
