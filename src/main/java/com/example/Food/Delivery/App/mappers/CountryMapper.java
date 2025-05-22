package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.Country.CountryDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.*;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public Country toEntity(CountryDto dto) {
        return new Country(dto.getCountryName());
    }

    public CountryDto toDto(Country country) {
       return new CountryDto(country.getCountryName());
    }
}
