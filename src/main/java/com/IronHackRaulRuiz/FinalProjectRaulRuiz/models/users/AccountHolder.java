package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class AccountHolder extends User {

    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;
    //@Embedded
    //private Address mailingAddress;

}
