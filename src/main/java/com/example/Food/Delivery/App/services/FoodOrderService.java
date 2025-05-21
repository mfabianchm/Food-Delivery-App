package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.User;
import com.example.Food.Delivery.App.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    @Transactional
    public FoodOrderResponseDto createOrder(FoodOrderRequestDto dto) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        FoodOrder order = new FoodOrder(
                user,
                orderStatusRepository.findById(dto.getOrderStatusId()).orElseThrow(),
                deliveryDriverRepository.findById(dto.getDeliveryDriverId()).orElseThrow(),
                userAddressRepository.findById(dto.getUserAddressId()).orElseThrow(),
                restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(),
                dto.getDeliveryFee(),
                dto.getTotalAmount(),
                dto.getRequestedDeliveryDateTime()
        );

        return mapToDto(foodOrderRepository.save(order));
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
        return new FoodOrderResponseDto(
                order.getId(),
                order.getOrderDateTime(),
                order.getDeliveryFee(),
                order.getTotalAmount(),
                order.getRequestedDeliveryDateTime(),
                order.getCustDriverRating(),
                order.getCustRestaurantRating(),
                order.getOrderStatus().getStatusName(),
                order.getUser().getFullName(),
                order.getDeliveryDriver().getFirstName(),
                order.getUserAddress().toString(),
                order.getRestaurant().getName()
        );
    }
}