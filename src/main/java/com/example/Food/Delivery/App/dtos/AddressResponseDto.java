package com.example.Food.Delivery.App.dtos;

public class AddressResponseDto {
    private Long id;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String state;
    private String zipCode;
    private String countryName;

    public AddressResponseDto() {
    }

    public AddressResponseDto(Long id, String street, String apartmentNumber, String houseNumber, String city, String state, String zipCode, String countryName) {
        this.id = id;
        this.street = street;
        this.apartmentNumber = apartmentNumber;
        this.houseNumber = houseNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}


