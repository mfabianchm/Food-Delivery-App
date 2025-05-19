package com.example.Food.Delivery.App.dtos.MenuItem;

import java.math.BigDecimal;

public class MenuItemResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long restaurantId;
    private String restaurantName;

    public MenuItemResponseDto() {
    }

    public MenuItemResponseDto(Long id, String name, String description, BigDecimal price, Long restaurantId, String restaurantName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
