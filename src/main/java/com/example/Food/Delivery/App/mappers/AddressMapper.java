package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.Address.AddressResponseDto;
import com.example.Food.Delivery.App.dtos.UserAddress.UserAddressResponseDto;
import com.example.Food.Delivery.App.entities.Address;
import com.example.Food.Delivery.App.entities.UserAddress;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressResponseDto toDto(Address address) {
        return new AddressResponseDto(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getApartmentNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry().getCountryName()
        );
    }
}
