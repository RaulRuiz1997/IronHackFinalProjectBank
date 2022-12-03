package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Address {

    private String name;
    private Integer numberHouse;
    private String city;
    private Integer zipCode;

}
