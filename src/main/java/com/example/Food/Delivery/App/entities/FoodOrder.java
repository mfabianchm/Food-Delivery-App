package com.example.Food.Delivery.App.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @DecimalMin(value = "0.00", inclusive = false, message = "Delivery fee must be greater than zero")
    private BigDecimal deliveryFee;

    @Column(name = "total_amount", nullable = false)
    @DecimalMin(value = "0.00", inclusive = false, message = "Total amount must be greater than zero")
    private BigDecimal totalAmount;

    @Column(name = "requested_delivery_datetime", nullable = false)
    private LocalDateTime requestedDeliveryDateTime;

    @Column(name = "cust_driver_rating", nullable = false)
    @Min(value = 1, message = "Driver rating must be at least 1")
    @Max(value = 5, message = "Driver rating must be at most 5")
    private BigDecimal custDriverRating;

    @Column(name = "cust_restaurant_rating", nullable = false)
    @Min(value = 1, message = "Restaurant rating must be at least 1")
    @Max(value = 5, message = "Restaurant rating must be at most 5")
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


    public FoodOrder() {}

    public FoodOrder(User user, OrderStatus orderStatus, DeliveryDriver deliveryDriver, UserAddress userAddress, Restaurant restaurant, BigDecimal deliveryFee, BigDecimal totalAmount, LocalDateTime requestedDeliveryDateTime) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.deliveryDriver = deliveryDriver;
        this.userAddress = userAddress;
        this.restaurant = restaurant;
        this.deliveryFee = deliveryFee;
        this.totalAmount = totalAmount;
        this.requestedDeliveryDateTime = requestedDeliveryDateTime;
        this.custDriverRating = BigDecimal.ZERO;  // Default value for ratings
        this.custRestaurantRating = BigDecimal.ZERO; // Default value for ratings
    }

    public Long getId() {
        return id;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeliveryDriver getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(DeliveryDriver deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @PrePersist
    public void prePersist() {
        if (this.custDriverRating == null) {
            this.custDriverRating = BigDecimal.ZERO;
        }
        if (this.custRestaurantRating == null) {
            this.custRestaurantRating = BigDecimal.ZERO;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the same object
        if (o == null || getClass() != o.getClass()) return false; // Check if different class or null
        FoodOrder foodOrder = (FoodOrder) o;
        return Objects.equals(id, foodOrder.id); // Compare id field (assuming it's the key field)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use id for consistent hash calculation
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                ", deliveryFee=" + deliveryFee +
                ", totalAmount=" + totalAmount +
                ", requestedDeliveryDateTime=" + requestedDeliveryDateTime +
                ", custDriverRating=" + custDriverRating +
                ", custRestaurantRating=" + custRestaurantRating +
                ", orderStatus=" + orderStatus +
                ", user=" + user +
                ", deliveryDriver=" + deliveryDriver +
                ", userAddress=" + userAddress +
                ", restaurant=" + restaurant +
                '}';
    }

}
