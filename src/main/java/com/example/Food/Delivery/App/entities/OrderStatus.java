package com.example.Food.Delivery.App.entities;

import com.example.Food.Delivery.App.enums.OrderStatusType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private OrderStatusType statusType;

    @OneToMany(mappedBy = "orderStatus")
    private List<FoodOrder> foodOrders;
}
