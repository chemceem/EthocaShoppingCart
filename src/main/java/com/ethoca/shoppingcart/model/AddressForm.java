package com.ethoca.shoppingcart.model;

import javax.validation.constraints.NotNull;

/**
 * Created by Chemcee. M. C on 20-11-2016.
 */
public class AddressForm {

    @NotNull(message = "First Name required.")
    private String firstName;
    @NotNull(message = "Last Name required.")
    private String lastName;
    @NotNull(message = "Address required.")
    private String address;
    private String addressLine2;
    @NotNull(message = "City required.")
    private String city;
    private String state;
    private String country;
    private String zip;
    private String phoneNumber;

    public AddressForm() {
    }

    public AddressForm(String firstName, String lastName, String address, String addressLine2, String city, String state, String country, String zip, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
