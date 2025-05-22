package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.Restaurant.RestaurantSummaryDto;
import com.example.Food.Delivery.App.entities.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantSummaryMapper {

    public RestaurantSummaryDto toDto(Restaurant restaurant) {
        return new RestaurantSummaryDto(
                restaurant.getId(),
                restaurant.getName()
        );
    }
}


