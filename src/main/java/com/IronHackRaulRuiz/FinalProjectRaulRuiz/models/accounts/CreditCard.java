package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    private final Integer MIN_CREDIT_LIMIT = 100;
    private final Integer MAX_CREDIT_LIMIT = 100000;
    private final Double MAX_INTEREST_RATE = 0.2;
    private final Double MIN_INTEREST_RATE = 0.1;

    private Integer creditLimit = 100;
    private Double interestRate = 0.2;

    public CreditCard(Double balance, User primaryOwner, User secondaryOwner, Double penaltyFee, Integer creditLimit, Double interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
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

}
