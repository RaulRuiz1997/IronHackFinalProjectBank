package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account {

    private static final BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal("0.5");

    private static final BigDecimal MINIMUM_INTEREST_RATE = new BigDecimal("0.0025");

    private static final BigDecimal MAXIMUM_BALANCE = new BigDecimal("1000.0");

    private static final BigDecimal MINIMUM_BALANCE2 = new BigDecimal("100.0");

    @NotNull
    private BigDecimal minimumBalance = MINIMUM_BALANCE2;
    @NotNull
    private String secretKey;
    @NotNull
    private BigDecimal interestRate = MINIMUM_INTEREST_RATE;

    private LocalDate lastInterestApplied = LocalDate.now();

    public Savings() {
    }

    public Savings(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, BigDecimal minimumBalance, String secretKey, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, status);
        setMinimumBalance(minimumBalance);
        setSecretKey(secretKey);
        setInterestRate(interestRate);
    }


    // Valores preestablecidos para guardar un interestRate. Mínimo: 0.0025, Máximo, 0.5
    public void setInterestRate(BigDecimal interestRate) {

        if (interestRate.compareTo(MAXIMUM_INTEREST_RATE) > 0) this.interestRate = MAXIMUM_INTEREST_RATE;
        else if (interestRate.compareTo(MINIMUM_INTEREST_RATE) < 0) this.interestRate = MINIMUM_INTEREST_RATE;
        else this.interestRate = interestRate;

    }

    // Valores preestablecidos para guardar un minimumBalance. Mínimo: 100, Máximo, 1000
    public void setMinimumBalance(BigDecimal minimumBalance) {

        if (minimumBalance.compareTo(MAXIMUM_BALANCE) > 0) this.minimumBalance = MAXIMUM_BALANCE;
        else if (minimumBalance.compareTo(MINIMUM_BALANCE2) < 0) this.minimumBalance = MINIMUM_BALANCE2;
        else this.minimumBalance = minimumBalance;

    }

    @Override
    public BigDecimal getBalance() {
        return checkInterestRate(super.getBalance());
    }

    // Si el balance es menos que el minimum balance, le aplicamos el penalty fee
    @Override
    public void setBalance(BigDecimal balance) {

        // Comprobamos si ha pasado un año para añadirle o no el interest rate
        balance = checkInterestRate(balance);

        if (balance.compareTo(MINIMUM_BALANCE2) < 0) {

            super.setBalance(balance.subtract(getPENALTY_FEE()));

        } else {

            super.setBalance(balance);

        }

    }

    private BigDecimal checkInterestRate(BigDecimal balance) {

        Period period = Period.between(getLastInterestApplied(), LocalDate.now());
        int daysPassed = Math.abs(period.getDays());

        // Si ha pasado N años, reiniciamos la variable lastInterestApplied y devolvemos el balance con los intereses
        // Si no, solo devolvemos el balance sin los intereses aplicados
        if (daysPassed >= 365) {

            // Le sumamos los años para no perder años
            lastInterestApplied = lastInterestApplied.plusYears(period.getYears());

            return balance.multiply(getInterestRate().multiply(BigDecimal.valueOf(period.getYears())));

        } else {

            return balance;

        }

    }

    private LocalDate getLastInterestApplied() {

        if (lastInterestApplied == null) {
            lastInterestApplied = LocalDate.now();
        }

        return lastInterestApplied;

    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getInterestRate() {
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
