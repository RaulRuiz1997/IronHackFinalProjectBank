package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

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

    private Integer creditLimit = 100;
    private Double interestRate = 0.2;

}
