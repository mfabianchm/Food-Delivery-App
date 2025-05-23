package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverRequestDto;
import com.example.Food.Delivery.App.dtos.DeliveryDriver.DeliveryDriverResponseDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.dtos.OrderStatus.ChangeOrderStatusRequestDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.services.DeliveryDriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.Food.Delivery.App.enums.OrderStatusType;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@PreAuthorize("hasRole('DRIVER')")
public class DeliveryDriverController {
    private final DeliveryDriverService deliveryDriverService;

    public DeliveryDriverController(DeliveryDriverService deliveryDriverService) {
        this.deliveryDriverService = deliveryDriverService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
    @GetMapping("/{id}")
    public DeliveryDriverResponseDto getDriverById(@PathVariable Long id) {
        return deliveryDriverService.getDriverById(id);
    }


    // === ADMIN-ONLY ENDPOINTS ===

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<DeliveryDriverResponseDto> getAll() {
        return deliveryDriverService.getAllDrivers();
    }


    // === DRIVER-ONLY ENDPOINTS ===

    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping("/me")
    public DeliveryDriverResponseDto updateMyProfile(@PathVariable Long id, @RequestBody @Valid DeliveryDriverRequestDto dto) {
        return deliveryDriverService.updateDriver(id, dto);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyProfile(@PathVariable Long id) {
        deliveryDriverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/{orderId}/accept")
    public ResponseEntity<FoodOrderResponseDto> acceptOrder(@PathVariable Long orderId) {
        FoodOrderResponseDto updatedOrder = deliveryDriverService.acceptOrder(orderId);
        return ResponseEntity.ok(updatedOrder);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<FoodOrderResponseDto> changeOrderStatus(
            @PathVariable Long orderId,
            @RequestBody @Valid ChangeOrderStatusRequestDto request) {

        FoodOrderResponseDto updatedOrder = deliveryDriverService.updateOrderStatus(orderId, request.getNewStatus().name());
        return ResponseEntity.ok(updatedOrder);

    }

}
