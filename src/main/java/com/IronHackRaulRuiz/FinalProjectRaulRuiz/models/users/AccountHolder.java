package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Account;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    @Embedded
    private Address primaryAddress;

    //@NotNull
    //@Embedded
    //private Address mailingAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner", cascade= CascadeType.ALL)
    private List<Account> accountListPrimaryOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "secondaryOwner", cascade= CascadeType.ALL)
    private List<Account> accountListSecondaryOwner;

    public AccountHolder() {
    }

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public List<Account> getAccountListPrimaryOwner() {
        return accountListPrimaryOwner;
    }

    public void setAccountListPrimaryOwner(List<Account> accountListPrimaryOwner) {
        this.accountListPrimaryOwner = accountListPrimaryOwner;
    }

    public List<Account> getAccountListSecondaryOwner() {
        return accountListSecondaryOwner;
    }

    public void setAccountListSecondaryOwner(List<Account> accountListSecondaryOwner) {
        this.accountListSecondaryOwner = accountListSecondaryOwner;
    }

    @Override
    public String toString() {

        return "AccountHolder{" +
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", primaryAddress=" + primaryAddress +
                ", accountListPrimaryOwner=" + accountListPrimaryOwner +
                ", accountListSecondaryOwner=" + accountListSecondaryOwner +
                '}';

    }

}
