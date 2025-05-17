package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
