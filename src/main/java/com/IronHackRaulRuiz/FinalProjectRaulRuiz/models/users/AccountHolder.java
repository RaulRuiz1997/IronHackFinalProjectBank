package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;

    //@Embedded
    //private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner", cascade= CascadeType.ALL)
    private List<Account> accountListPrimaryOwner;

    @OneToMany(mappedBy = "secondaryOwner", cascade= CascadeType.ALL)
    private List<Account> accountListSecondaryOwner;

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

}
