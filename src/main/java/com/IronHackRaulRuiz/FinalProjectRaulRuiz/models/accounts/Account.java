package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El balance no puede ser NULL!")
    private BigDecimal balance;
    @NotNull(message = "El primaryOwner no puede ser NULL!")
    @ManyToOne
    private AccountHolder primaryOwner;
    @Nullable
    @ManyToOne
    private AccountHolder secondaryOwner;
    private final BigDecimal PENALTY_FEE = new BigDecimal("40.0");
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "El creationDate no puede ser NULL!")
    private LocalDate creationDate;
    @NotNull(message = "El status no puede ser NULL!")
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    private final BigDecimal MINIMUM_BALANCE = new BigDecimal("250.0");

    public Account() {
    }

    public Account(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        this.creationDate = LocalDate.now();
        setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setBalance(BigDecimal balance, BigDecimal MINIMUM_BALANCE) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPENALTY_FEE() {
        return PENALTY_FEE;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = LocalDate.now();
    }

    public StatusAccount getStatus() {
        return status;
    }

    public void setStatus(StatusAccount status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", primaryOwner=" + primaryOwner +
                ", secondaryOwner=" + secondaryOwner +
                ", PENALTY_FEE=" + PENALTY_FEE +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';

    }

}
