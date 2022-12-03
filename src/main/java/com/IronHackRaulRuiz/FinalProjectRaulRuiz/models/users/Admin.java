package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    public Account createAccount() {
        return null;
    }

}
