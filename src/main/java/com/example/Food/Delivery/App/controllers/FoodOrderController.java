package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderRequestDto;
import com.example.Food.Delivery.App.dtos.FoodOrder.FoodOrderResponseDto;
import com.example.Food.Delivery.App.services.FoodOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/orders")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    @Autowired
    public FoodOrderController(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    @PostMapping
    public ResponseEntity<FoodOrderResponseDto> createOrder(@RequestBody @Valid FoodOrderRequestDto dto, Principal principal) {
        String email = principal.getName();
        return new ResponseEntity<>(foodOrderService.createOrder(dto, email), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodOrderResponseDto> updateOrder(
            @PathVariable Long id,
            @RequestBody @Valid FoodOrderRequestDto dto) {
        return ResponseEntity.ok(foodOrderService.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        foodOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrderResponseDto> getOrderById(@PathVariable Long id) {
        FoodOrderResponseDto orderDto = foodOrderService.getOrderById(id);
        return ResponseEntity.ok(orderDto);
    }
}