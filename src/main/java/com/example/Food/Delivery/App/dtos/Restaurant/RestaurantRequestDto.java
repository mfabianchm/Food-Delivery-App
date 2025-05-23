package com.example.Food.Delivery.App.dtos.Restaurant;

import com.example.Food.Delivery.App.dtos.Address.AddressRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RestaurantRequestDto {
    @NotBlank
    private String name;

    private Long addressId;

    private AddressRequestDto address;

    public RestaurantRequestDto(String name, Long addressId, AddressRequestDto address) {
        this.name = name;
        this.addressId = addressId;
        this.address = address;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public AddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDto address) {
        this.address = address;
    }
}
