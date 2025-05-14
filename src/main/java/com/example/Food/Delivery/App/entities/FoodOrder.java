package com.example.Food.Delivery.App.entities;
import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "food_orders")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "order_datetime", nullable = false, updatable = false)
    private LocalDateTime orderDateTime;

    @Column(name = "delivery_fee", nullable = false)
    private BigDecimal deliveryFee;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "requested_delivery_datetime", nullable = false)
    private LocalDateTime requestedDeliveryDateTime;

    @Column(name = "cust_driver_rating", nullable = false)
    private BigDecimal custDriverRating;

    @Column(name = "cust_restaurant_rating", nullable = false)
    private BigDecimal custRestaurantRating;

    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "delivery_driver_id", nullable = false)
    private DeliveryDriver deliveryDriver;

    @ManyToOne
    @JoinColumn(name = "user_address_id", nullable = false)
    private UserAddress userAddress;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}
