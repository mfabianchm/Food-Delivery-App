package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByName(String name);
}
