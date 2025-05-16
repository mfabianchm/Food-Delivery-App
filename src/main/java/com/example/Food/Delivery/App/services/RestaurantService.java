package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.AddressDto;
import com.example.Food.Delivery.App.dtos.RestaurantResponseDto;
import com.example.Food.Delivery.App.entities.Address;
import com.example.Food.Delivery.App.entities.Restaurant;
import com.example.Food.Delivery.App.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService (RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private RestaurantResponseDto mapToDto(Restaurant restaurant) {
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                mapAddressToDto(restaurant.getAddress())
        );
    }

    private AddressDto mapAddressToDto(Address address) {
        if (address == null) return null;

        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getApartmentNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry() != null ? address.getCountry().getCountryName() : null
        );
    }


}
