package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.transfers;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idSenderAccount;
    private Long idRecipientAccount;
    private String nameRecipientOwner;
    private BigDecimal balance;

    public Transaction() {
    }

    public Transaction(Long idSenderAccount, Long idRecipientAccount, String nameRecipientOwner, BigDecimal balance) {
        this.idSenderAccount = idSenderAccount;
        this.idRecipientAccount = idRecipientAccount;
        this.nameRecipientOwner = nameRecipientOwner;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Long getIdSenderAccount() {
        return idSenderAccount;
    }

    public void setIdSenderAccount(Long idSenderAccount) {
        this.idSenderAccount = idSenderAccount;
    }

    public Long getIdRecipientAccount() {
        return idRecipientAccount;
    }

    public void setIdRecipientAccount(Long idRecipientAccount) {
        this.idRecipientAccount = idRecipientAccount;
    }

    public String getNameRecipientOwner() {
        return nameRecipientOwner;
    }

    public void setNameRecipientOwner(String nameRecipientOwner) {
        this.nameRecipientOwner = nameRecipientOwner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
