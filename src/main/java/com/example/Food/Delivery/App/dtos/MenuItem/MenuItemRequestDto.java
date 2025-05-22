package com.example.Food.Delivery.App.dtos.MenuItem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class MenuItemRequestDto {

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 5, max = 500)
    private String description;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull
    private Long restaurantId;

    public MenuItemRequestDto() {
    }

    public MenuItemRequestDto(String name, String description, BigDecimal price, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public @NotNull @Size(min = 2, max = 100) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 2, max = 100) String name) {
        this.name = name;
    }

    public @NotNull @Size(min = 5, max = 500) String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @Size(min = 5, max = 500) String description) {
        this.description = description;
    }

    public @NotNull @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than zero") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than zero") BigDecimal price) {
        this.price = price;
    }

    public @NotNull Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NotNull Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
