package com.example.Food.Delivery.App.dtos;

import jakarta.validation.constraints.NotBlank;

public class CountryDto {
    @NotBlank
    private String countryName;

    public CountryDto() {}

    public CountryDto(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
