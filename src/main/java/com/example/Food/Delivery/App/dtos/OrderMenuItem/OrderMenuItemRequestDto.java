package com.example.Food.Delivery.App.dtos.OrderMenuItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderMenuItemRequestDto {
    @NotNull(message = "Menu item ID is required")
    private Long menuItemId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantityOrdered;

    public OrderMenuItemRequestDto() {
    }

    public OrderMenuItemRequestDto(Long menuItemId, Integer quantityOrdered) {
        this.menuItemId = menuItemId;
        this.quantityOrdered = quantityOrdered;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
