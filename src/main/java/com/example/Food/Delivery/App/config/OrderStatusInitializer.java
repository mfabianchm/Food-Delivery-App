package com.example.Food.Delivery.App.config;

import com.example.Food.Delivery.App.entities.OrderStatus;
import com.example.Food.Delivery.App.enums.OrderStatusType;
import com.example.Food.Delivery.App.repositories.OrderStatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusInitializer {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusInitializer(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @PostConstruct
    public void init() {
        for (OrderStatusType type : OrderStatusType.values()) {
            orderStatusRepository.findByStatusName(type.name())
                    .orElseGet(() -> orderStatusRepository.save(new OrderStatus(type.name())));
        }
    }
}