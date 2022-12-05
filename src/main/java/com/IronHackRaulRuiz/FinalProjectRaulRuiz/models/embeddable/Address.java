package com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String name;
    private Integer numberHouse;
    private String city;
    private Integer zipCode;

    public Address() {
    }

    public Address(String name, Integer numberHouse, String city, Integer zipCode) {
        this.name = name;
        this.numberHouse = numberHouse;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(Integer numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {

        return "Address{" +
                "name='" + name + '\'' +
                ", numberHouse=" + numberHouse +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                '}';

    }

}
