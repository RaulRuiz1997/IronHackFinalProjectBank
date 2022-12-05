package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {

    private String secretKey;

    public StudentChecking(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, status);
        this.secretKey = secretKey;
    }

}
