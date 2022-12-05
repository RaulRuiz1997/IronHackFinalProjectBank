package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends User {

    @NotNull
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String name, String hashedKey) {
        super(name);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    @Override
    public String toString() {

        return "ThirdParty{" +
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", hashedKey='" + hashedKey + '\'' +
                '}';

    }

}
