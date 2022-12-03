package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "primaryOwner")
    List<Account> accountList;

    public User(String name) {
        this.name = name;
    }

}
