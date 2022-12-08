package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    @NotNull
    private final Integer MONTHLY_MAINTENANCE_FEE = 12;
    @NotNull
    private final BigDecimal MINIMUM_BALANCE = new BigDecimal("250.0");
    @NotNull
    private String secretKey;

    public Checking() {
    }

    public Checking(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, status);
        setSecretKey(secretKey);
    }

    // Si el balance es menos que el minimum balance, le aplicamos el penalty fee
    @Override
    public void setBalance(BigDecimal balance, BigDecimal MINIMUM_BALANCE) {

        // Si el balance es menor a MINIMUM_BALANCE dará -1
        if (balance.compareTo(MINIMUM_BALANCE) < 0) {
            super.setBalance(balance.subtract(getPENALTY_FEE()));
        } else {
            super.setBalance(balance);
        }

    }

    public Integer getMonthlyMaintenanceFee() {
        return MONTHLY_MAINTENANCE_FEE;
    }

    public BigDecimal getMinimumBalance() {
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
