package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemResponseDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.OrderMenuItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMenuItemMapper {

    public OrderMenuItemResponseDto toDto(OrderMenuItem orderMenuItem) {
        return new OrderMenuItemResponseDto(
                orderMenuItem.getId(),
                orderMenuItem.getMenuItem().getName(),
                orderMenuItem.getMenuItem().getDescription(),
                orderMenuItem.getMenuItem().getPrice(),
                orderMenuItem.getQuantity_ordered(),
                orderMenuItem.getMenuItem().getRestaurant().getName()
        );
    }
}
