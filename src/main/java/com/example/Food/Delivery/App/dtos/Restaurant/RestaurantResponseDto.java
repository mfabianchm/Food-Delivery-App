package com.example.Food.Delivery.App.dtos.Restaurant;

import com.example.Food.Delivery.App.dtos.Address.AddressResponseDto;

public class RestaurantResponseDto {
    private Long id;
    private String name;
    private AddressResponseDto address;

    public RestaurantResponseDto() {
    }

    public RestaurantResponseDto(Long id, String name, AddressResponseDto address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressResponseDto  getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }
}
