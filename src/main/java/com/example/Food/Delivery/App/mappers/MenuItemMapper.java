package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemResponseDto;
import com.example.Food.Delivery.App.entities.*;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {
    private final RestaurantSummaryMapper restaurantSummaryMapper;

    public MenuItemMapper (RestaurantSummaryMapper restaurantSummaryMapper) {
        this.restaurantSummaryMapper = restaurantSummaryMapper;
    }

    public MenuItem toEntity(MenuItemRequestDto dto, Restaurant restaurant) {
        return new MenuItem(
                dto.getName().trim(),
                dto.getDescription().trim(),
                dto.getPrice(),
                restaurant
        );
    }

    public MenuItemResponseDto toDto(MenuItem menuItem) {
        return new MenuItemResponseDto(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                restaurantSummaryMapper.toDto(menuItem.getRestaurant())
        );
    }
}
