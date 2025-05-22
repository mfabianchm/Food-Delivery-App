package com.example.Food.Delivery.App.dtos.OrderMenuItem;

import java.math.BigDecimal;

public class OrderMenuItemResponseDto {

    private Long id;
    private String menuItemName;
    private String menuItemDescription;
    private BigDecimal menuItemPrice;
    private Integer quantityOrdered;
    private String restaurantName;

    public OrderMenuItemResponseDto() {
    }

    public OrderMenuItemResponseDto(Long id, String menuItemName, String menuItemDescription, BigDecimal menuItemPrice, Integer quantityOrdered, String restaurantName) {
        this.id = id;
        this.menuItemName = menuItemName;
        this.menuItemDescription = menuItemDescription;
        this.menuItemPrice = menuItemPrice;
        this.quantityOrdered = quantityOrdered;
        this.restaurantName = restaurantName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public BigDecimal getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(BigDecimal menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
