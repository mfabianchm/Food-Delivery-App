package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.DeliveryDriver;
import com.example.Food.Delivery.App.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
    Optional<DeliveryDriver> findByUser(User user);
}
