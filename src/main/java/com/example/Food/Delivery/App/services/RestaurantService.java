package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.AddressRequestDto;
import com.example.Food.Delivery.App.dtos.AddressResponseDto;
import com.example.Food.Delivery.App.dtos.RestaurantRequestDto;
import com.example.Food.Delivery.App.dtos.RestaurantResponseDto;
import com.example.Food.Delivery.App.entities.Address;
import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.Restaurant;
import com.example.Food.Delivery.App.repositories.AddressRepository;
import com.example.Food.Delivery.App.repositories.CountryRepository;
import com.example.Food.Delivery.App.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;

    public RestaurantService (
            RestaurantRepository restaurantRepository,
            AddressRepository addressRepository,
            CountryRepository countryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
    }


    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::mapToRestaurantResponseDto)
                .collect(Collectors.toList());
    }


    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        // Save new Address in database
        Address address = mapToAddressEntity(dto.getAddress());
        address = addressRepository.save(address);

        // Create and save Restaurant
        Restaurant restaurant = new Restaurant(dto.getName(), address);
        restaurant = restaurantRepository.save(restaurant);

        // Return mapped DTO
        return mapToRestaurantResponseDto(restaurant);
    }

    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto dto) {
        // 1. Find the restaurant
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));

        // 2. Update name
        restaurant.setName(dto.getName());

        // 3. Update Address
        Address address = restaurant.getAddress(); // Get existing address
        AddressRequestDto addressDto = dto.getAddress();

        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setApartmentNumber(addressDto.getApartmentNumber());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());

        // Update the country if needed (optional: fetch by name or id)
        if (addressDto.getCountryId() != null) {
            Country country = countryRepository.findById(addressDto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            address.setCountry(country);
        }

        addressRepository.save(address); // Save updated address

        // Save restaurant
        restaurantRepository.save(restaurant);

        // Return updated DTO
        return mapToRestaurantResponseDto(restaurant);
    }


    //This method takes an AddressDto and converts it into an Address entity
    private Address mapToAddressEntity(AddressRequestDto dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        address.setApartmentNumber(dto.getApartmentNumber());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());

        Country country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found with id: " + dto.getCountryId()));
        address.setCountry(country);

        return address;
    }


    private RestaurantResponseDto mapToRestaurantResponseDto(Restaurant restaurant) {
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                mapAddressToDto(restaurant.getAddress())
        );
    }

    private AddressResponseDto mapAddressToDto(Address address) {
        if (address == null) return null;

        String country = (address.getCountry() != null) ? address.getCountry().getCountryName() : null;

        return new AddressResponseDto(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getApartmentNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                country
        );
    }


}
