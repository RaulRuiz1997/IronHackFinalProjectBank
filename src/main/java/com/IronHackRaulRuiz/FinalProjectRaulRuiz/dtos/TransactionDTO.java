package com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos;

import java.math.BigDecimal;

public class TransactionDTO {

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
