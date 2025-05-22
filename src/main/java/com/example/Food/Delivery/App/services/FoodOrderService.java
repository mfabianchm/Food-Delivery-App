package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.User;
import com.example.Food.Delivery.App.mappers.FoodOrderMapper;
import com.example.Food.Delivery.App.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;
    private final UserAddressRepository userAddressRepository;
    private final RestaurantRepository restaurantRepository;

    private final FoodOrderMapper foodOrderMapper;

    @Autowired
    public FoodOrderService(FoodOrderRepository foodOrderRepository,
                            UserRepository userRepository,
                            OrderStatusRepository orderStatusRepository,
                            DeliveryDriverRepository deliveryDriverRepository,
                            UserAddressRepository userAddressRepository,
                            RestaurantRepository restaurantRepository,
                            FoodOrderMapper foodOrderMapper) {
        this.foodOrderRepository = foodOrderRepository;
        this.userRepository = userRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
        this.userAddressRepository = userAddressRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodOrderMapper = foodOrderMapper;
    }


    @Transactional
    public FoodOrderResponseDto createOrder(FoodOrderRequestDto dto, String userEmail) {
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

        return foodOrderMapper.toDto(foodOrderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        FoodOrder order = foodOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        foodOrderRepository.deleteById(id);
    }

    @Transactional
    public FoodOrderResponseDto updateOrder(Long id, FoodOrderRequestDto dto) {
        FoodOrder order = foodOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));

        order.setDeliveryFee(dto.getDeliveryFee());
        order.setTotalAmount(dto.getTotalAmount());
        if (dto.getRequestedDeliveryDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Requested delivery time must be in the future");
        }
        order.setRequestedDeliveryDateTime(dto.getRequestedDeliveryDateTime());
        order.setUser(user);
        order.setOrderStatus(orderStatusRepository.findById(dto.getOrderStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Order status not found with id: " + dto.getOrderStatusId())));
        order.setDeliveryDriver(deliveryDriverRepository.findById(dto.getDeliveryDriverId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + dto.getDeliveryDriverId())));
        order.setUserAddress(userAddressRepository.findById(dto.getUserAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + dto.getUserAddressId())));
        order.setRestaurant(restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + dto.getRestaurantId())));

        return foodOrderMapper.toDto(foodOrderRepository.save(order));
    }

    public FoodOrderResponseDto getOrderById(Long id) {
        FoodOrder order = foodOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        return foodOrderMapper.toDto(order);
    }
}