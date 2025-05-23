package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.Restaurant.RestaurantResponseDto;
import com.example.Food.Delivery.App.entities.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    private final AddressMapper addressMapper;

    public RestaurantMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }


    public RestaurantResponseDto toDto(Restaurant restaurant) {
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                addressMapper.toDto(restaurant.getAddress())
        );
    }
}
