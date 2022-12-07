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
public class CreditCard extends Account {

    private final Integer MIN_CREDIT_LIMIT = 100;
    private final Integer MAX_CREDIT_LIMIT = 100000;
    private final BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.2");
    private final BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.1");
    @NotNull
    private Integer creditLimit = 100;
    @NotNull
    private BigDecimal interestRate = MAX_INTEREST_RATE;

    private LocalDate lastInterestApplied = LocalDate.now();

    public CreditCard() {
    }

    public CreditCard(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, Integer creditLimit, BigDecimal interestRate) {
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

    public BigDecimal getMAX_INTEREST_RATE() {
        return MAX_INTEREST_RATE;
    }

    public BigDecimal getMIN_INTEREST_RATE() {
        return MIN_INTEREST_RATE;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    // Valores preestablecidos para guardar un creditLimit. Mínimo: 100, Máximo, 100000
    public void setCreditLimit(Integer creditLimit) {

        if (creditLimit > MAX_CREDIT_LIMIT) this.creditLimit = MAX_CREDIT_LIMIT;
        else if (creditLimit < MIN_CREDIT_LIMIT) this.creditLimit = MIN_CREDIT_LIMIT;
        else this.creditLimit = creditLimit;

    }

    // Valores preestablecidos para guardar un interestRate. Mínimo: 0.1, Máximo, 0.2
    public void setInterestRate(BigDecimal interestRate) {

        if (interestRate.compareTo(MAX_INTEREST_RATE) > 0) this.interestRate = MAX_INTEREST_RATE;
        else if (interestRate.compareTo(MIN_INTEREST_RATE) < 0) this.interestRate = MIN_INTEREST_RATE;
        else this.interestRate = interestRate;

    }

    // todo: preguntar esto con los profes, lo he hecho porque en el enunciado dice, cuando se accede al saldo, determinar
    //  si ha pasado un mes, y las únicas veces que se accede al saldo es en los getters y setters
    @Override
    public BigDecimal getBalance() {

        return checkInterestRate(super.getBalance());

    }

    // Comprobamos antes el interés mensual
    @Override
    public void setBalance(BigDecimal balance) {

        // Comprobamos si ha pasado un año para añadirle o no el interest rate
        balance = checkInterestRate(balance);

        super.setBalance(balance);

    }

    // todo: mirar esto con los profes
    //  creo que esta bien, hacer un test en CreditCardRepositoryTest de este método checkInterestRate()
    //  tiene que ser publica o privada? si es privada no puedo hacer test
    private BigDecimal checkInterestRate(BigDecimal balance) {

        Period period = Period.between(getLastInterestApplied(), LocalDate.now());
        int monthsPassed = Math.abs(period.getMonths());

        // Si ha pasado 1 mes, reiniciamos la variable lastInterestApplied y devolvemos el balance con los intereses
        // Si no, solo devolvemos el balance sin los intereses aplicados
        if (monthsPassed >= 1) {

            lastInterestApplied = lastInterestApplied.plusMonths(monthsPassed);

            // Devolvemos el balance * los intereses dividido entre los meses del año así le sumamos los intereses
            // que le pertocan por mes
            return balance.multiply((getInterestRate().divide(new BigDecimal("12")).multiply(BigDecimal.valueOf(monthsPassed))));

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
