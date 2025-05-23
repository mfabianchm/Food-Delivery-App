package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.*;
import org.springframework.stereotype.Component;

@Component
public class FoodOrderMapper {

    private final AddressMapper addressMapper;

    public FoodOrderMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

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
                order.getOrderStatus().getStatusType().name(),
                order.getUser().getFirstName(),
                order.getDeliveryDriver().getFirstName(),
                addressMapper.toDto(order.getUserAddress().getAddress()),
                order.getRestaurant().getName()
        );
    }
}
