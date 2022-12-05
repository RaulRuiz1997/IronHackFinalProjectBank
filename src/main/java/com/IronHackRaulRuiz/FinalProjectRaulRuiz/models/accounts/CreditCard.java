package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    private final Integer MIN_CREDIT_LIMIT = 100;
    private final Integer MAX_CREDIT_LIMIT = 100000;
    private final Double MAX_INTEREST_RATE = 0.2;
    private final Double MIN_INTEREST_RATE = 0.1;
    @NotNull
    private Integer creditLimit = 100;
    @NotNull
    private Double interestRate = MAX_INTEREST_RATE;

    public CreditCard() {
    }

    public CreditCard(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Integer creditLimit, Double interestRate) {
        super(balance, primaryOwner, secondaryOwner, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public Integer getMIN_CREDIT_LIMIT() {
        return MIN_CREDIT_LIMIT;
    }

    public Integer getMAX_CREDIT_LIMIT() {
        return MAX_CREDIT_LIMIT;
    }

    public Double getMAX_INTEREST_RATE() {
        return MAX_INTEREST_RATE;
    }

    public Double getMIN_INTEREST_RATE() {
        return MIN_INTEREST_RATE;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    // Valores preestablecidos para guardar un creditLimit. Mínimo: 100, Máximo, 100000
    public void setCreditLimit(Integer creditLimit) {

        if (creditLimit > MAX_CREDIT_LIMIT) this.creditLimit = MAX_CREDIT_LIMIT;
        else if (creditLimit < MIN_CREDIT_LIMIT) this.creditLimit = MIN_CREDIT_LIMIT;
        else this.creditLimit = creditLimit;

    }

    // Valores preestablecidos para guardar un interestRate. Mínimo: 0.1, Máximo, 0.2
    public void setInterestRate(Double interestRate) {

        if (interestRate > MAX_INTEREST_RATE) this.interestRate = MAX_INTEREST_RATE;
        else if (interestRate < MIN_INTEREST_RATE) this.interestRate = MIN_INTEREST_RATE;
        else this.interestRate = interestRate;

    }

    @Override
    public String toString() {

        return "CreditCard{" +
                "id=" + getId() +
                ", balance=" + getBalance() +
                ", primaryOwner=" + getPrimaryOwner() +
                ", secondaryOwner=" + getSecondaryOwner() +
                ", PENALTY_FEE=" + getPENALTY_FEE() +
                ", creationDate=" + getCreationDate() +
                ", status=" + getStatus() +
                ", MIN_CREDIT_LIMIT=" + MIN_CREDIT_LIMIT +
                ", MAX_CREDIT_LIMIT=" + MAX_CREDIT_LIMIT +
                ", MAX_INTEREST_RATE=" + MAX_INTEREST_RATE +
                ", MIN_INTEREST_RATE=" + MIN_INTEREST_RATE +
                ", creditLimit=" + creditLimit +
                ", interestRate=" + interestRate +
                '}';

    }

}
