package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El balance no puede ser NULL!")
    private Double balance;
    @NotNull(message = "El primaryOwner no puede ser NULL!")
    @ManyToOne
    private AccountHolder primaryOwner;
    @Nullable
    @ManyToOne
    private AccountHolder secondaryOwner;
    private final Double PENALTY_FEE = 40.0;
    @NotNull(message = "El creationDate no puede ser NULL!")
    private LocalDate creationDate;

    @NotNull(message = "El status no puede ser NULL!")
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    private final Double MINIMUM_BALANCE = 250.0;

    public Account() {
    }

    public Account(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        this.creationDate = LocalDate.now();
        setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public void setBalance(Double balance, Double MINIMUM_BALANCE) {
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

    public Double getPENALTY_FEE() {
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
