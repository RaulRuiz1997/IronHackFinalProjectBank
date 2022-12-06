package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account {

    private static final Double MAXIMUM_INTEREST_RATE = 0.5;

    private static final Double MINIMUM_INTEREST_RATE = 0.0025;

    private static final Double MAXIMUM_BALANCE = 1000.0;

    private static final Double MINIMUM_BALANCE2 = 100.0;

    @NotNull
    private Double minimumBalance = MINIMUM_BALANCE2; // todo: mirar esto que no me vale son el 2 al final
    @NotNull
    private String secretKey;
    @NotNull
    private Double interestRate = MINIMUM_INTEREST_RATE;

    private LocalDate lastInterestApplied;

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

    // todo: preguntar esto con los profes, lo he hecho porque en el enunciado dice, cuanso se accede al saldo, determinar
    //  si ha pasado un año, y las unicas veces que se accede al saldo es en los getters y setters
    @Override
    public Double getBalance() {
        return checkInterestRate(getBalance());
    }

    // Si el balance es menos que el minimum balance, le aplicamos el penalty fee
    @Override
    public void setBalance(Double balance) {

        // Comprobamos si ha pasado un año para añadirle o no el interest rate
        balance = checkInterestRate(balance);

        if (balance < MINIMUM_BALANCE2) {

            super.setBalance(balance - getPENALTY_FEE());

        } else {

            super.setBalance(balance);

        }

    }

    // todo: mirar esto con los profes
    // todo: creo que esta bien, hacer un test en SavingRepositoryTest de este método checkInterestRate()
    private Double checkInterestRate(Double balance) {

        Period period = Period.between(getLastInterestApplied(), LocalDate.now());
        int daysPassed = Math.abs(period.getDays());

        // Si ha pasado 1 año, reiniciamos la variable lastInterestApplied y devolvemos el balance con los intereses
        // Si no, solo devolvemos el balance sin los intereses aplicados
        if (daysPassed >= 365) {

            lastInterestApplied = LocalDate.now();

            return balance * getInterestRate();

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
