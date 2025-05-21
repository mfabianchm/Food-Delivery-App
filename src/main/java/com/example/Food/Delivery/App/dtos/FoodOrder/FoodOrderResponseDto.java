package com.example.Food.Delivery.App.dtos.FoodOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FoodOrderResponseDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private BigDecimal deliveryFee;
    private BigDecimal totalAmount;
    private LocalDateTime requestedDeliveryDateTime;
    private Integer custDriverRating;
    private Integer custRestaurantRating;
    private String orderStatusName;
    private String userFullName;
    private String deliveryDriverName;
    private String userAddress;
    private String restaurantName;

    public FoodOrderResponseDto(
            Long id,
            LocalDateTime orderDateTime,
            BigDecimal deliveryFee,
            BigDecimal totalAmount,
            LocalDateTime requestedDeliveryDateTime,
            Integer custDriverRating,
            Integer custRestaurantRating,
            String orderStatusName,
            String userFullName,
            String deliveryDriverName,
            String userAddress,
            String restaurantName
    ) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.deliveryFee = deliveryFee;
        this.totalAmount = totalAmount;
        this.requestedDeliveryDateTime = requestedDeliveryDateTime;
        this.custDriverRating = custDriverRating;
        this.custRestaurantRating = custRestaurantRating;
        this.orderStatusName = orderStatusName;
        this.userFullName = userFullName;
        this.deliveryDriverName = deliveryDriverName;
        this.userAddress = userAddress;
        this.restaurantName = restaurantName;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getRequestedDeliveryDateTime() {
        return requestedDeliveryDateTime;
    }

    public BigDecimal getCustDriverRating() {
        return custDriverRating;
    }

    public BigDecimal getCustRestaurantRating() {
        return custRestaurantRating;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getDeliveryDriverName() {
        return deliveryDriverName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}