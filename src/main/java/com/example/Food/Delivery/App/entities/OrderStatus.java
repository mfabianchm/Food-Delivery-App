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

    @Column(nullable = false, unique = true)
    private String statusName;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY)
    private List<FoodOrder> foodOrders;

    public OrderStatus() {}

    public OrderStatus(String statusName) {
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(List<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(statusName, that.statusName) && Objects.equals(foodOrders, that.foodOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusName, foodOrders);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                ", foodOrders=" + foodOrders +
                '}';
    }
}
