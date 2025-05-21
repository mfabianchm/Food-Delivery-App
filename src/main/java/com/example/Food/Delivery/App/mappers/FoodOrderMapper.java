package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.*;
import org.springframework.stereotype.Component;

@Component
public class FoodOrderMapper {

    public FoodOrder toEntity(
            FoodOrderRequestDto dto,
            User user,
            OrderStatus orderStatus,
            DeliveryDriver driver,
            UserAddress address,
            Restaurant restaurant
    ) {
        return new FoodOrder(
                user,
                orderStatus,
                driver,
                address,
                restaurant,
                dto.getDeliveryFee(),
                dto.getTotalAmount(),
                dto.getRequestedDeliveryDateTime()
        );
    }

    public FoodOrderResponseDto toDto(FoodOrder order) {
        return new FoodOrderResponseDto(
                order.getId(),
                order.getOrderDateTime(),
                order.getDeliveryFee(),
                order.getTotalAmount(),
                order.getRequestedDeliveryDateTime(),
                order.getCustDriverRating(),
                order.getCustRestaurantRating(),
                order.getOrderStatus().getStatusName(),
                order.getUser().getFullName(),               // assuming getFullName() exists
                order.getDeliveryDriver().getFirstName(),     // assuming getFullName() exists
                order.getUserAddress().getAddress(),     // assuming getFullAddress() exists
                order.getRestaurant().getName()
        );
    }
}
