package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
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
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

}
