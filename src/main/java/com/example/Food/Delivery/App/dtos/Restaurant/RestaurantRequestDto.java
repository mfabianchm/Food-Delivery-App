package com.example.Food.Delivery.App.dtos.Restaurant;

import com.example.Food.Delivery.App.dtos.Address.AddressRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RestaurantRequestDto {
    @NotBlank
    private String name;

    @Valid
    @NotNull
    private AddressRequestDto address;

    public RestaurantRequestDto() {}

    public RestaurantRequestDto(String name, AddressRequestDto address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDto address) {
        this.address = address;
    }
}
