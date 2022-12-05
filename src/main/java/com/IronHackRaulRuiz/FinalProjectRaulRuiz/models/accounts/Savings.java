package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account {

    private static final Double MAXIMUM_INTEREST_RATE = 0.5;

    private static final Double MINIMUM_INTEREST_RATE = 0.0;

    private static final Double MAXIMUM_BALANCE = 1000.0;

    private static final Double MINIMUM_BALANCE2 = 100.0;
    @NotNull

    private Double minimumBalance = MINIMUM_BALANCE2;
    @NotNull
    private String secretKey;
    @NotNull
    private Double interestRate = MINIMUM_INTEREST_RATE;

    public Savings() {
    }

    public Savings(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Double minimumBalance, String secretKey, Double interestRate) {
        super(balance, primaryOwner, secondaryOwner, status);
        this.minimumBalance = minimumBalance;
        this.secretKey = secretKey;
        this.interestRate = interestRate;
    }

    // Valores preestablecidos para guardar un interestRate. Mínimo: 0.0025, Máximo, 0.5
    public void setInterestRate(Double interestRate) {

        if (interestRate > MAXIMUM_INTEREST_RATE) this.interestRate = MAXIMUM_INTEREST_RATE;
        else if (interestRate < MINIMUM_INTEREST_RATE) this.interestRate = MINIMUM_INTEREST_RATE;
        else this.interestRate = interestRate;

    }

    // Valores preestablecidos para guardar un minimumBalance. Mínimo: 100, Máximo, 1000
    public void setMinimumBalance(Double minimumBalance) {

        if (minimumBalance > MAXIMUM_BALANCE) this.minimumBalance = MAXIMUM_BALANCE;
        else if (minimumBalance < MINIMUM_BALANCE2) this.minimumBalance = MINIMUM_BALANCE2;
        else this.minimumBalance = minimumBalance;

    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "id=" + getId() +
                ", balance=" + getBalance() +
                ", primaryOwner=" + getPrimaryOwner() +
                ", secondaryOwner=" + getSecondaryOwner() +
                ", PENALTY_FEE=" + getPENALTY_FEE() +
                ", creationDate=" + getCreationDate() +
                ", status=" + getStatus() +
                ", minimumBalance=" + minimumBalance +
                ", secretKey='" + secretKey + '\'' +
                ", interestRate=" + interestRate +
                '}';
    }

}
