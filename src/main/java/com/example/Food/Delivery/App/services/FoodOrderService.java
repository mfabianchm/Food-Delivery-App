package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;
    private final UserAddressRepository userAddressRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodOrderService(FoodOrderRepository foodOrderRepository,
                            UserRepository userRepository,
                            OrderStatusRepository orderStatusRepository,
                            DeliveryDriverRepository deliveryDriverRepository,
                            UserAddressRepository userAddressRepository,
                            RestaurantRepository restaurantRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.userRepository = userRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
        this.userAddressRepository = userAddressRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public FoodOrderResponseDto createOrder(FoodOrderRequestDto dto) {
        FoodOrder order = new FoodOrder(
                userRepository.findById(dto.getUserId()).orElseThrow(),
                orderStatusRepository.findById(dto.getOrderStatusId()).orElseThrow(),
                deliveryDriverRepository.findById(dto.getDeliveryDriverId()).orElseThrow(),
                userAddressRepository.findById(dto.getUserAddressId()).orElseThrow(),
                restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(),
                dto.getDeliveryFee(),
                dto.getTotalAmount(),
                dto.getRequestedDeliveryDateTime()
        );

        order = foodOrderRepository.save(order);
        return mapToDto(order);
    }

    public void deleteOrder(Long id) {
        foodOrderRepository.deleteById(id);
    }

    public FoodOrderResponseDto updateOrder(Long id, FoodOrderRequestDto dto) {
        FoodOrder order = foodOrderRepository.findById(id).orElseThrow();

        order.setDeliveryFee(dto.getDeliveryFee());
        order.setTotalAmount(dto.getTotalAmount());
        order.setRequestedDeliveryDateTime(dto.getRequestedDeliveryDateTime());
        order.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        order.setOrderStatus(orderStatusRepository.findById(dto.getOrderStatusId()).orElseThrow());
        order.setDeliveryDriver(deliveryDriverRepository.findById(dto.getDeliveryDriverId()).orElseThrow());
        order.setUserAddress(userAddressRepository.findById(dto.getUserAddressId()).orElseThrow());
        order.setRestaurant(restaurantRepository.findById(dto.getRestaurantId()).orElseThrow());

        return mapToDto(foodOrderRepository.save(order));
    }

    private FoodOrderResponseDto mapToDto(FoodOrder order) {
        FoodOrderResponseDto dto = new FoodOrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setDeliveryFee(order.getDeliveryFee());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setRequestedDeliveryDateTime(order.getRequestedDeliveryDateTime());
        dto.setCustDriverRating(order.getCustDriverRating());
        dto.setCustRestaurantRating(order.getCustRestaurantRating());
        dto.setOrderStatusName(order.getOrderStatus().getStatusName());
        dto.setUserFullName(order.getUser().getFullName());
        dto.setDeliveryDriverName(order.getDeliveryDriver().getName());
        dto.setUserAddress(order.getUserAddress().toString());
        dto.setRestaurantName(order.getRestaurant().getName());
        return dto;
    }
}