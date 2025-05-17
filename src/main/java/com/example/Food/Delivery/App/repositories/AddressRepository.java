package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Address;
import com.example.Food.Delivery.App.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
