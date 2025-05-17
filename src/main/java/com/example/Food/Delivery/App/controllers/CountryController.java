package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.Country.CountryDto;
import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.services.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@PreAuthorize("hasRole('ADMIN')")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody @Valid CountryDto dto) {
        Country saved = countryService.createCountry(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
