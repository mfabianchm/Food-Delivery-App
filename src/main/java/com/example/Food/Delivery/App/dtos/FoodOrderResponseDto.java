package com.example.Food.Delivery.App.dtos;

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

    private Long orderStatusId;
    private Long userId;
    private Long deliveryDriverId;
    private Long userAddressId;
    private Long restaurantId;

    public FoodOrderResponseDto() {

    }

    public FoodOrderResponseDto(Long id, LocalDateTime orderDateTime, BigDecimal deliveryFee, BigDecimal totalAmount, LocalDateTime requestedDeliveryDateTime, BigDecimal custDriverRating, BigDecimal custRestaurantRating, Long orderStatusId, Long userId, Long deliveryDriverId, Long userAddressId, Long restaurantId) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.deliveryFee = deliveryFee;
        this.totalAmount = totalAmount;
        this.requestedDeliveryDateTime = requestedDeliveryDateTime;
        this.custDriverRating = custDriverRating;
        this.custRestaurantRating = custRestaurantRating;
        this.orderStatusId = orderStatusId;
        this.userId = userId;
        this.deliveryDriverId = deliveryDriverId;
        this.userAddressId = userAddressId;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getRequestedDeliveryDateTime() {
        return requestedDeliveryDateTime;
    }

    public void setRequestedDeliveryDateTime(LocalDateTime requestedDeliveryDateTime) {
        this.requestedDeliveryDateTime = requestedDeliveryDateTime;
    }

    public BigDecimal getCustDriverRating() {
        return custDriverRating;
    }

    public void setCustDriverRating(BigDecimal custDriverRating) {
        this.custDriverRating = custDriverRating;
    }

    public BigDecimal getCustRestaurantRating() {
        return custRestaurantRating;
    }

    public void setCustRestaurantRating(BigDecimal custRestaurantRating) {
        this.custRestaurantRating = custRestaurantRating;
    }

    public Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeliveryDriverId() {
        return deliveryDriverId;
    }

    public void setDeliveryDriverId(Long deliveryDriverId) {
        this.deliveryDriverId = deliveryDriverId;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
