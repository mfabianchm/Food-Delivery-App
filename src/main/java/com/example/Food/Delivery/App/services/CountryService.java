package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.CountryDto;
import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country createCountry(CountryDto dto) {

        if (countryRepository.existsByCountryNameIgnoreCase(dto.getCountryName())) {
            throw new IllegalArgumentException("Country already exists with name: " + dto.getCountryName());
        }

        Country country = new Country(dto.getCountryName().trim());
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
