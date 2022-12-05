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

    @NotNull
    private Double balance;
    // todo: @NotNull hacer una comprobación en el controller para que no le metan null porque simplemente con la anotacion NotNull
    //  no sirve
    @NotNull
    @ManyToOne
    private AccountHolder primaryOwner;
    @Nullable
    @ManyToOne
    private AccountHolder secondaryOwner;
    private final Double PENALTY_FEE = 40.0;
    @NotNull
    private LocalDate creationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    public Account() {
    }

    public Account(Double balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, StatusAccount status) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = LocalDate.now();
        this.status = status;
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
