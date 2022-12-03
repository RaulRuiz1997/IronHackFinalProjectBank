package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;
    @ManyToOne
    private User primaryOwner;
    @ManyToOne
    private User secondaryOwner;
    // todo: Tiene que ser opcional?
    // private Optional<User> secondaryOwner;
    private Double penaltyFee = 40.0;

    public Account(Double balance, User primaryOwner, User secondaryOwner, Double penaltyFee) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
    }


}
