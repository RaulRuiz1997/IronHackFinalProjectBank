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
    private Integer monthlyMaintenanceFee = 12;
    @NotNull
    private Double minimumBalance = 250.0;
    @NotNull
    private String secretKey;

    public Checking() {
    }

    public Checking(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Integer monthlyMaintenanceFee, Double minimumBalance, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, status);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
        this.secretKey = secretKey;
    }

    public Integer getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Integer monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Double minimumBalance) {
        this.minimumBalance = minimumBalance;
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
                ", monthlyMaintenanceFee=" + monthlyMaintenanceFee +
                ", minimumBalance=" + minimumBalance +
                ", secretKey='" + secretKey + '\'' +
                '}';

    }

}
