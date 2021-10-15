package com.codecool.shop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends BaseModel{
    private List<Product> productsInCart;
    private LocalDateTime creationTime;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String country;
    private String city;
    private String zipcode;
    private String address;

    public Order(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
        this.creationTime = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return fullName;
    }

    @Override
    public void setName(String name) {
        this.fullName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
