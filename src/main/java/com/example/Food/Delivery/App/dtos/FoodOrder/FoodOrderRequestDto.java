package com.example.Food.Delivery.App.dtos.FoodOrder;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FoodOrderRequestDto {

    @NotNull(message = "Delivery fee is required")
    @DecimalMin(value = "0.01", message = "Delivery fee must be greater than zero")
    private BigDecimal deliveryFee;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", message = "Total amount must be greater than zero")
    private BigDecimal totalAmount;

    @NotNull(message = "Requested delivery datetime is required")
    @Future(message = "Requested delivery datetime must be in the future")
    private LocalDateTime requestedDeliveryDateTime;

    @NotNull(message = "Order status id is required")
    private Long orderStatusId;

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Delivery driver id is required")
    private Long deliveryDriverId;

    @NotNull(message = "User address id is required")
    private Long userAddressId;

    @NotNull(message = "Restaurant id is required")
    private Long restaurantId;

    public @NotNull(message = "Delivery fee is required") @DecimalMin(value = "0.01", message = "Delivery fee must be greater than zero") BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(@NotNull(message = "Delivery fee is required") @DecimalMin(value = "0.01", message = "Delivery fee must be greater than zero") BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public @NotNull(message = "Restaurant id is required") Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NotNull(message = "Restaurant id is required") Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public @NotNull(message = "User address id is required") Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(@NotNull(message = "User address id is required") Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public @NotNull(message = "Delivery driver id is required") Long getDeliveryDriverId() {
        return deliveryDriverId;
    }

    public void setDeliveryDriverId(@NotNull(message = "Delivery driver id is required") Long deliveryDriverId) {
        this.deliveryDriverId = deliveryDriverId;
    }

    public @NotNull(message = "User id is required") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User id is required") Long userId) {
        this.userId = userId;
    }

    public @NotNull(message = "Order status id is required") Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(@NotNull(message = "Order status id is required") Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public @NotNull(message = "Requested delivery datetime is required") @Future(message = "Requested delivery datetime must be in the future") LocalDateTime getRequestedDeliveryDateTime() {
        return requestedDeliveryDateTime;
    }

    public void setRequestedDeliveryDateTime(@NotNull(message = "Requested delivery datetime is required") @Future(message = "Requested delivery datetime must be in the future") LocalDateTime requestedDeliveryDateTime) {
        this.requestedDeliveryDateTime = requestedDeliveryDateTime;
    }

    public @NotNull(message = "Total amount is required") @DecimalMin(value = "0.01", message = "Total amount must be greater than zero") BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NotNull(message = "Total amount is required") @DecimalMin(value = "0.01", message = "Total amount must be greater than zero") BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
