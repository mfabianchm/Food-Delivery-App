package com.example.Food.Delivery.App.dtos.UserAddress;

import com.example.Food.Delivery.App.dtos.Address.AddressResponseDto;

public class UserAddressResponseDto {
    private Long id;
    private AddressResponseDto address;

    public UserAddressResponseDto(Long id, AddressResponseDto address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressResponseDto getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }
}
