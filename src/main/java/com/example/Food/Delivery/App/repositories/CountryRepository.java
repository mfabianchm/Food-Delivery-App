package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Address;
import com.example.Food.Delivery.App.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository  extends JpaRepository<Country, Long> {
    boolean existsByCountryNameIgnoreCase(String countryName);
}
