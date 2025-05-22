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

    @PostMapping
    public ResponseEntity<DeliveryDriverResponseDto> create(@RequestBody @Valid DeliveryDriverRequestDto dto) {
        return new ResponseEntity<>(deliveryDriverService.createDriver(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<DeliveryDriverResponseDto> getAll() {
        return deliveryDriverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public DeliveryDriverResponseDto getById(@PathVariable Long id) {
        return deliveryDriverService.getDriverById(id);
    }

    @PutMapping("/{id}")
    public DeliveryDriverResponseDto update(@PathVariable Long id, @RequestBody @Valid DeliveryDriverRequestDto dto) {
        return deliveryDriverService.updateDriver(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryDriverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/accept")
    public ResponseEntity<FoodOrderResponseDto> acceptOrder(@PathVariable Long orderId) {
        FoodOrderResponseDto updatedOrder = deliveryDriverService.acceptOrder(orderId);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<FoodOrderResponseDto> changeOrderStatus(
            @PathVariable Long orderId,
            @RequestBody @Valid ChangeOrderStatusRequestDto request) {

        FoodOrderResponseDto updatedOrder = deliveryDriverService.updateOrderStatus(orderId, request.getNewStatus().name());
        return ResponseEntity.ok(updatedOrder);

    }

}
