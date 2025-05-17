package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
}
