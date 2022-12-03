package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Checking extends Account {

    private Integer montlyMaintenanceFee = 12;
    private Double minimumBalance = 250.0;
    private String secretKey;
    private LocalDate creationDate;
    private StatusAccount status;

    public Checking(Double balance, User primaryOwner, User secondaryOwner, Double penaltyFee, Integer montlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee);
        this.montlyMaintenanceFee = montlyMaintenanceFee;
    }

}
