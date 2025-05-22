package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverRequestDto;
import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverResponseDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.entities.DeliveryDriver;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.OrderStatus;
import com.example.Food.Delivery.App.enums.OrderStatusType;
import com.example.Food.Delivery.App.mappers.DeliveryDriverMapper;
import com.example.Food.Delivery.App.mappers.FoodOrderMapper;
import com.example.Food.Delivery.App.repositories.DeliveryDriverRepository;
import com.example.Food.Delivery.App.repositories.FoodOrderRepository;
import com.example.Food.Delivery.App.repositories.OrderStatusRepository;
import com.example.Food.Delivery.App.utils.AuthenticatedUserUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryDriverService {
    private final FoodOrderRepository foodOrderRepository;
    private final AuthenticatedUserUtil authenticatedUserUtil;
    private final OrderStatusRepository orderStatusRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;

    private final DeliveryDriverMapper deliveryDriverMapper;
    private final FoodOrderMapper foodOrderMapper;

    public DeliveryDriverService(FoodOrderRepository foodOrderRepository,
                                 AuthenticatedUserUtil authenticatedUserUtil,
                                 OrderStatusRepository orderStatusRepository,
                                 DeliveryDriverRepository deliveryDriverRepository,
                                 DeliveryDriverMapper deliveryDriverMapper,
                                 FoodOrderMapper foodOrderMapper) {
        this.foodOrderRepository = foodOrderRepository;
        this.authenticatedUserUtil = authenticatedUserUtil;
        this.orderStatusRepository = orderStatusRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
        this.deliveryDriverMapper = deliveryDriverMapper;
        this.foodOrderMapper = foodOrderMapper;
    }

    public DeliveryDriverResponseDto createDriver(DeliveryDriverRequestDto dto) {
       DeliveryDriver deliveryDriver = deliveryDriverMapper.toEntity(dto);
       deliveryDriverRepository.save(deliveryDriver);
        return deliveryDriverMapper.toDto(deliveryDriver);
    }

    public List<DeliveryDriverResponseDto> getAllDrivers() {
        return deliveryDriverRepository.findAll().stream()
                .map(deliveryDriverMapper::toDto)
                .collect(Collectors.toList());
    }

    public DeliveryDriverResponseDto getDriverById(Long id) {
        DeliveryDriver driver = deliveryDriverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        return deliveryDriverMapper.toDto(driver);
    }

    public DeliveryDriverResponseDto updateDriver(Long id, DeliveryDriverRequestDto dto) {
        DeliveryDriver driver = deliveryDriverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));

        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPhoneNumber(dto.getPhoneNumber());

        return deliveryDriverMapper.toDto(deliveryDriverRepository.save(driver));
    }

    public void deleteDriver(Long id) {
        if (!deliveryDriverRepository.existsById(id)) {
            throw new EntityNotFoundException("Driver not found");
        }
        deliveryDriverRepository.deleteById(id);
    }


    @Transactional
    public FoodOrderResponseDto acceptOrder(Long orderId) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        DeliveryDriver driver = authenticatedUserUtil.getAuthenticatedDeliveryDriver();

        // Assigns driver to order
        order.setDeliveryDriver(driver);

        // Change order status to ACCEPTED
        OrderStatus acceptedStatus = orderStatusRepository.findByStatusName(OrderStatusType.ACCEPTED.name())
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus ACCEPTED not found"));
        order.setOrderStatus(acceptedStatus);

        return foodOrderMapper.toDto(foodOrderRepository.save(order));
    }

    @Transactional
    public FoodOrderResponseDto updateOrderStatus(Long orderId, String newStatusName) {
        // Get authenticated driver
        DeliveryDriver driver = authenticatedUserUtil.getAuthenticatedDeliveryDriver();

        // Find the order that belongs to this driver
        FoodOrder order = foodOrderRepository.findByIdAndDeliveryDriverId(orderId, driver.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found or not assigned to this driver"));

        if (order.getOrderStatus().getStatusName().equalsIgnoreCase(newStatusName)) {
            return foodOrderMapper.toDto(order);
        }

        // Find the OrderStatus entity by statusName
        OrderStatus newStatus = orderStatusRepository.findByStatusName(newStatusName)
                .orElseThrow(() -> new RuntimeException("Order status not found"));

        // Update order status
        order.setOrderStatus(newStatus);

        // Save the updated order
        return foodOrderMapper.toDto(foodOrderRepository.save(order));
    }
}
