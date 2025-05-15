package com.example.Food.Delivery.App.dtos;

import jakarta.validation.constraints.NotNull;

//implement this LATER!
public class FoodOrderRequestDto {
    @NotNull(message = "User ID cannot be null")
    private Long foodOrderId;
}
