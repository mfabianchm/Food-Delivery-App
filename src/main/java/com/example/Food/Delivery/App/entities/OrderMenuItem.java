package com.example.Food.Delivery.App.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
@Table(name = "order_menu_items")
public class OrderMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food_order_id")
    private FoodOrder foodOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(nullable = false)
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity_ordered;


    public OrderMenuItem() {}

    public OrderMenuItem(FoodOrder foodOrder, MenuItem menuItem, Integer quantity_ordered) {
        this.foodOrder = foodOrder;
        this.menuItem = menuItem;
        this.quantity_ordered = quantity_ordered;
    }


    public Long getId() {
        return id;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getQuantity_ordered() {
        return quantity_ordered;
    }

    public void setQuantity_ordered(Integer quantity_ordered) {
        this.quantity_ordered = quantity_ordered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMenuItem)) return false;
        OrderMenuItem that = (OrderMenuItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderMenuItem{" +
                "id=" + id +
                ", menuItem=" + menuItem.getName() +
                ", quantity_orderes=" + quantity_ordered +
                '}';
    }
}
