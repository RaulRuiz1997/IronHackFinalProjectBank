package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transfers;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePrimaryOwner;
    private String nameSecondaryOwner;
    private Long idRecipientAccount;
    private BigDecimal balance;

    public Transfer() {
    }

    public Transfer(String namePrimaryOwner, String nameSecondaryOwner, Long idRecipientAccount, BigDecimal balance) {
        setNamePrimaryOwner(namePrimaryOwner);
        setNameSecondaryOwner(nameSecondaryOwner);
        setIdRecipientAccount(idRecipientAccount);
        setBalance(balance);
    }

    public Long getId() {
        return id;
    }

    public String getNamePrimaryOwner() {
        return namePrimaryOwner;
    }

    public void setNamePrimaryOwner(String namePrimaryOwner) {
        this.namePrimaryOwner = namePrimaryOwner;
    }

    public String getNameSecondaryOwner() {
        return nameSecondaryOwner;
    }

    public void setNameSecondaryOwner(String nameSecondaryOwner) {
        this.nameSecondaryOwner = nameSecondaryOwner;
    }

    public Long getIdRecipientAccount() {
        return idRecipientAccount;
    }

    public void setIdRecipientAccount(Long idRecipientAccount) {
        this.idRecipientAccount = idRecipientAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
