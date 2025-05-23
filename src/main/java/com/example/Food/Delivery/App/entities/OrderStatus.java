package com.example.Food.Delivery.App.entities;

import com.example.Food.Delivery.App.enums.OrderStatusType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order-status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private OrderStatusType statusType;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY)
    private List<FoodOrder> foodOrders;

    public OrderStatus() {}

    public OrderStatus(OrderStatusType statusType) {
        this.statusType = statusType;
    }

    public OrderStatus(OrderStatusType statusType, List<FoodOrder> foodOrders) {
        this.statusType = statusType;
        this.foodOrders = foodOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(OrderStatusType statusType) {
        this.statusType = statusType;
    }

    public List<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(List<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(id, that.id) && statusType == that.statusType && Objects.equals(foodOrders, that.foodOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusType, foodOrders);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", statusType=" + statusType +
                ", foodOrders=" + foodOrders +
                '}';
    }
}
