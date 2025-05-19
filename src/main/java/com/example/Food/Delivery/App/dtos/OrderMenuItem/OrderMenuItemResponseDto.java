package com.example.Food.Delivery.App.dtos.OrderMenuItem;

public class OrderMenuItemResponseDto {

    private Long id;
    private String menuItemName;
    private Integer quantityOrdered;

    public OrderMenuItemResponseDto() {
    }

    public OrderMenuItemResponseDto(Long id, String menuItemName, Integer quantityOrdered) {
        this.id = id;
        this.menuItemName = menuItemName;
        this.quantityOrdered = quantityOrdered;
    }

    public Long getId() {
        return id;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
