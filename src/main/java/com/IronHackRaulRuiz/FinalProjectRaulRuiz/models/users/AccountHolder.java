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
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "ADDRESS_NAME")),
            @AttributeOverride(name = "numberHouse", column = @Column(name = "ADDRESS_NUMBER_HOUSE")),
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "ADDRESS_ZIP_CODE"))
    })
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "MAILING_ADDRESS_NAME")),
            @AttributeOverride(name = "numberHouse", column = @Column(name = "MAILING_ADDRESS_NUMBER_HOUSE")),
            @AttributeOverride(name = "city", column = @Column(name = "MAILING_ADDRESS_CITY")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "MAILING_ADDRESS_ZIP_CODE"))
    })
    private Address mailingAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner", cascade= CascadeType.ALL)
    private List<Account> accountListPrimaryOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "secondaryOwner", cascade= CascadeType.ALL)
    private List<Account> accountListSecondaryOwner;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name, password);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
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

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
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
