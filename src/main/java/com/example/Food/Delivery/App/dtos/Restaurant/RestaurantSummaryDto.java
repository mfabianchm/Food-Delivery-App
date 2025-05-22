package com.example.Food.Delivery.App.dtos.Restaurant;

public class RestaurantSummaryDto {
    private Long id;
    private String name;

    public RestaurantSummaryDto() {
    }

    public RestaurantSummaryDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
