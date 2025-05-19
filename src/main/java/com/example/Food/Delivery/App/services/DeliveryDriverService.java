package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverRequestDto;
import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverResponseDto;
import com.example.Food.Delivery.App.entities.DeliveryDriver;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.OrderStatus;
import com.example.Food.Delivery.App.enums.OrderStatusType;
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

    public DeliveryDriverService(FoodOrderRepository foodOrderRepository,
                                 AuthenticatedUserUtil authenticatedUserUtil,
                                 OrderStatusRepository orderStatusRepository,
                                 DeliveryDriverRepository deliveryDriverRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.authenticatedUserUtil = authenticatedUserUtil;
        this.orderStatusRepository = orderStatusRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public DeliveryDriverResponseDto createDriver(DeliveryDriverRequestDto dto) {
        DeliveryDriver driver = new DeliveryDriver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPhoneNumber(dto.getPhoneNumber());

        DeliveryDriver saved = deliveryDriverRepository.save(driver);
        return toDto(saved);
    }

    public List<DeliveryDriverResponseDto> getAllDrivers() {
        return deliveryDriverRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DeliveryDriverResponseDto getDriverById(Long id) {
        DeliveryDriver driver = deliveryDriverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        return toDto(driver);
    }

    public DeliveryDriverResponseDto updateDriver(Long id, DeliveryDriverRequestDto dto) {
        DeliveryDriver driver = deliveryDriverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));

        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPhoneNumber(dto.getPhoneNumber());

        return toDto(deliveryDriverRepository.save(driver));
    }

    public void deleteDriver(Long id) {
        if (!deliveryDriverRepository.existsById(id)) {
            throw new EntityNotFoundException("Driver not found");
        }
        deliveryDriverRepository.deleteById(id);
    }

    private DeliveryDriverResponseDto toDto(DeliveryDriver driver) {
        return new DeliveryDriverResponseDto(
                driver.getId(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getEmail(),
                driver.getPhoneNumber()
        );
    }

    @Transactional
    public FoodOrder acceptOrder(Long orderId) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        // Get authenticated driver
        /*Retrieving the DeliveryDriver entity inside the service lets you know which driver
        is performing the operation, so you can:
       1. Assign orders correctly
       2.Enforce driver-specific rules.*/
        DeliveryDriver driver = authenticatedUserUtil.getAuthenticatedDeliveryDriver();

        // Assigns driver to order
        order.setDeliveryDriver(driver);

        // Change order status to ACCEPTED
        OrderStatus acceptedStatus = orderStatusRepository.findByStatusName(OrderStatusType.ACCEPTED.name())
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus ACCEPTED not found"));

        order.setOrderStatus(acceptedStatus);

        return foodOrderRepository.save(order);
    }

    @Transactional
    public FoodOrder updateOrderStatus(Long orderId, String newStatusName) {
        // Get authenticated driver
        DeliveryDriver driver = authenticatedUserUtil.getAuthenticatedDeliveryDriver();

        // Find the order that belongs to this driver
        FoodOrder order = foodOrderRepository.findByIdAndDeliveryDriverId(orderId, driver.getId())
                .orElseThrow(() -> new RuntimeException("Order not found or not assigned to this driver"));

        // Find the OrderStatus entity by statusName
        OrderStatus newStatus = orderStatusRepository.findByStatusName(newStatusName)
                .orElseThrow(() -> new RuntimeException("Order status not found"));

        // Update order status
        order.setOrderStatus(newStatus);

        // Save the updated order
        return foodOrderRepository.save(order);
    }
}
