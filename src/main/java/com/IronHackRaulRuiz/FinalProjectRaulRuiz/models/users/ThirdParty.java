package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

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
public class ThirdParty extends User {

    private String hashedKey;

}
