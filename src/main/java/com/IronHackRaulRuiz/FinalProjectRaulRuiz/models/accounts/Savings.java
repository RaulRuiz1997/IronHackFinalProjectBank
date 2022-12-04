package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account {

    @Transient
    private final Double MAXIMUM_INTEREST_RATE = 0.5;
    @Transient
    private final Double MINIMUM_INTEREST_RATE = 0.0025;
    @Transient
    private final Double MAXIMUM_BALANCE = 1000.0;
    // todo: mirar esto de la variable MINIMUM_BALANCE que no deja ponerle ese nombre porque se duplica
    @Transient
    private final Double MINIMUM_BALANCE2 = 100.0;

    private Double minimumBalance = MINIMUM_BALANCE2;
    private String secretKey;
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private StatusAccount status;
    private Double interestRate = MINIMUM_INTEREST_RATE;

    public Savings(Double balance, User primaryOwner, User secondaryOwner, Double penaltyFee, Double minimumBalance, String secretKey, LocalDate creationDate, StatusAccount status, Double interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee);
        this.minimumBalance = minimumBalance;
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
        this.interestRate = interestRate;
    }

    // Valores preestablecidos para guardar un interestRate. Mínimo: 0.0025, Máximo, 0.5
    public void setInterestRate(Double interestRate) {

        if (interestRate > MAXIMUM_INTEREST_RATE) this.interestRate = MAXIMUM_INTEREST_RATE;
        else if (interestRate < 0) this.interestRate = MINIMUM_INTEREST_RATE;
        else this.interestRate = interestRate;

    }

    // Valores preestablecidos para guardar un minimumBalance. Mínimo: 100, Máximo, 1000
    public void setMinimumBalance(Double minimumBalance) {

        if (minimumBalance > MAXIMUM_BALANCE) this.minimumBalance = MAXIMUM_BALANCE;
        else if (minimumBalance < MINIMUM_BALANCE2) this.minimumBalance = MINIMUM_BALANCE2;
        else this.minimumBalance = minimumBalance;

    }
}
