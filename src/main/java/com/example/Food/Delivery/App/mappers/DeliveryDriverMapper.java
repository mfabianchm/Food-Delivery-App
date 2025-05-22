package com.example.Food.Delivery.App.mappers;

import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverRequestDto;
import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverResponseDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.entities.*;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDriverMapper {

    public DeliveryDriver toEntity(DeliveryDriverRequestDto dto) {
       return new DeliveryDriver(
               dto.getFirstName(),
               dto.getLastName(),
               dto.getEmail(),
               dto.getPhoneNumber()
       );
    }

    public DeliveryDriverResponseDto toDto(DeliveryDriver entity) {
        return new DeliveryDriverResponseDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }
}
