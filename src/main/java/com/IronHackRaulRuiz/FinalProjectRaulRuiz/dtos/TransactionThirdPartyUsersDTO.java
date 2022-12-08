package com.IronHackRaulRuiz.FinalProjectRaulRuiz.dtos;

import java.math.BigDecimal;

public class TransactionThirdPartyUsersDTO {

    private BigDecimal amount;
    private Long accountId;
    private String secretKey;

    private BigDecimal newBalance;

    public TransactionThirdPartyUsersDTO() {
    }

    /*public TransactionThirdPartyUsersDTO(BigDecimal amount, Long accountId, String secretKey) {
        this.amount = amount;
        this.accountId = accountId;
        this.secretKey = secretKey;
    }*/

    public TransactionThirdPartyUsersDTO(BigDecimal amount, Long idAffectedAccount, BigDecimal newBalance) {
        this.amount = amount;
        this.accountId = idAffectedAccount;
        this.newBalance = newBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

}
