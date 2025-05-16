package com.example.Food.Delivery.App.dtos;

public class RestaurantResponseDto {
    private Long id;
    private String name;
    private AddressDto address;

    public RestaurantResponseDto() {
    }

    public RestaurantResponseDto(Long id, String name, AddressDto address) {
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
