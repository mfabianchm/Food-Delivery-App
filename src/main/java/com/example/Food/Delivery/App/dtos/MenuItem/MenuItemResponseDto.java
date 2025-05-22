package com.example.Food.Delivery.App.dtos.MenuItem;

import com.example.Food.Delivery.App.dtos.Restaurant.RestaurantResponseDto;
import com.example.Food.Delivery.App.dtos.Restaurant.RestaurantSummaryDto;

import java.math.BigDecimal;

public class MenuItemResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private RestaurantSummaryDto restaurant;

    public MenuItemResponseDto() {
    }

    public MenuItemResponseDto(
            Long id,
            String name,
            String description,
            BigDecimal price,
            RestaurantSummaryDto restaurant
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RestaurantSummaryDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantSummaryDto restaurant) {
        this.restaurant = restaurant;
    }
}
