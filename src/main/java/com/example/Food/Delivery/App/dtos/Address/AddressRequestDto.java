package com.example.Food.Delivery.App.dtos.Address;

public class AddressRequestDto {
    private Long id;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String state;
    private String zipCode;
    private Long countryId;

    public AddressRequestDto() {
    }

    public AddressRequestDto(Long id, String street, String houseNumber, String apartmentNumber,
                             String city, String state, String zipCode, Long countryId) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.countryId = countryId;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getApartmentNumber() { return apartmentNumber; }
    public void setApartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public Long getCountryId() { return countryId; }
    public void setCountryId(Long countryId) { this.countryId = countryId; }
}
