package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.Address.AddressRequestDto;
import com.example.Food.Delivery.App.dtos.Address.AddressResponseDto;
import com.example.Food.Delivery.App.dtos.Restaurant.RestaurantRequestDto;
import com.example.Food.Delivery.App.dtos.Restaurant.RestaurantResponseDto;
import com.example.Food.Delivery.App.entities.Address;
import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.Restaurant;
import com.example.Food.Delivery.App.mappers.AddressMapper;
import com.example.Food.Delivery.App.mappers.RestaurantMapper;
import com.example.Food.Delivery.App.repositories.AddressRepository;
import com.example.Food.Delivery.App.repositories.CountryRepository;
import com.example.Food.Delivery.App.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;

    private final RestaurantMapper restaurantMapper;
    private final AddressMapper addressMapper;

    public RestaurantService (
            RestaurantRepository restaurantRepository,
            AddressRepository addressRepository,
            CountryRepository countryRepository,
            RestaurantMapper restaurantMapper,
            AddressMapper addressMapper
    ) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.restaurantMapper = restaurantMapper;
        this.addressMapper = addressMapper;
    }


    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }


    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {

        Address address;

        if (dto.getAddressId() != null) {
            address = addressRepository.findById(dto.getAddressId())
                    .orElseThrow(() -> new EntityNotFoundException("Address not found"));
        } else if (dto.getAddress() != null) {
            AddressRequestDto addressDto = dto.getAddress();

            // Get country entity
            Country country = countryRepository.findById(addressDto.getCountryId())
                    .orElseThrow(() -> new EntityNotFoundException("Country not found"));

            // Create and save address

            address = addressMapper.toEntity(addressDto, country);
            addressRepository.save(address);
        } else {
            throw new IllegalArgumentException("You must provide either addressId or address details.");
        }

        // Create and save restaurant
        Restaurant restaurant = new Restaurant(dto.getName(), address);

        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
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

        // Return updated DTO
        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }








}
