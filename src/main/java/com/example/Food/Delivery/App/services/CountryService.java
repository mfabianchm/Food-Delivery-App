package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.Country.CountryDto;
import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.mappers.CountryMapper;
import com.example.Food.Delivery.App.repositories.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public CountryDto createCountry(CountryDto dto) {

        if (countryRepository.existsByCountryNameIgnoreCase(dto.getCountryName())) {
            throw new IllegalArgumentException("Country already exists with name: " + dto.getCountryName());
        }

        Country country = new Country(dto.getCountryName().trim());

        return countryMapper.toDto(countryRepository.save(country));
    }

    public List<CountryDto> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
    }

    public CountryDto getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));
        return countryMapper.toDto(country);
    }

    public CountryDto updateCountry(Long id, CountryDto dto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        if (countryRepository.existsByCountryNameIgnoreCase(dto.getCountryName())
                && !country.getCountryName().equalsIgnoreCase(dto.getCountryName())) {
            throw new IllegalArgumentException("Another country already exists with the name: " + dto.getCountryName());
        }

        country.setCountryName(dto.getCountryName().trim());
        return countryMapper.toDto(countryRepository.save(country));
    }
}
