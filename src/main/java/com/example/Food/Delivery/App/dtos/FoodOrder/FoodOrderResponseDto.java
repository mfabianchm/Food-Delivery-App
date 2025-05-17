package com.example.Food.Delivery.App.dtos.FoodOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FoodOrderResponseDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private BigDecimal deliveryFee;
    private BigDecimal totalAmount;
    private LocalDateTime requestedDeliveryDateTime;
    private BigDecimal custDriverRating;
    private BigDecimal custRestaurantRating;

    private String orderStatusName;
    private String userFullName;
    private String deliveryDriverName;
    private String userAddress;
    private String restaurantName;

    public FoodOrderResponseDto() {
    }

    public FoodOrderResponseDto(Long id, String restaurantName, String userAddress, String deliveryDriverName, String orderStatusName, String userFullName, BigDecimal custRestaurantRating, BigDecimal custDriverRating, LocalDateTime requestedDeliveryDateTime, BigDecimal totalAmount, BigDecimal deliveryFee, LocalDateTime orderDateTime) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.userAddress = userAddress;
        this.deliveryDriverName = deliveryDriverName;
        this.orderStatusName = orderStatusName;
        this.userFullName = userFullName;
        this.custRestaurantRating = custRestaurantRating;
        this.custDriverRating = custDriverRating;
        this.requestedDeliveryDateTime = requestedDeliveryDateTime;
        this.totalAmount = totalAmount;
        this.deliveryFee = deliveryFee;
        this.orderDateTime = orderDateTime;
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