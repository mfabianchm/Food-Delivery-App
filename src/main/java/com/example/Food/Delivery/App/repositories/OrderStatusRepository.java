package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.OrderStatus;
import com.example.Food.Delivery.App.enums.OrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
//    Optional<OrderStatus> findByName(String name);
Optional<OrderStatus> findByStatusType(OrderStatusType statusType);
//    Optional<OrderStatus> findByStatusName(String statusName);
}
