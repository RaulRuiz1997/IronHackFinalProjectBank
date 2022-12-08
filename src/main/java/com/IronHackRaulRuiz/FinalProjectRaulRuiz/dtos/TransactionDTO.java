package com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class TransactionDTO {

    // devolver id de la transferencia, el dinero enviado, el balance actualizado de la cuenta emisora (DTO)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal moneySent;
    private BigDecimal newBalance;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, BigDecimal moneySent, BigDecimal balance) {
        this.moneySent= moneySent;
        this.newBalance = balance;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMoneySent() {
        return moneySent;
    }

    public void setMoneySent(BigDecimal moneySent) {
        this.moneySent = moneySent;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

}
