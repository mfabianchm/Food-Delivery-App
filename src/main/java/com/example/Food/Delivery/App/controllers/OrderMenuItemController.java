package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemResponseDto;
import com.example.Food.Delivery.App.services.OrderMenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/{orderId}/items")
public class OrderMenuItemController {

    private final OrderMenuItemService orderMenuItemService;

    public OrderMenuItemController(OrderMenuItemService orderMenuItemService) {
        this.orderMenuItemService = orderMenuItemService;
    }

    // ✅ Only customers can view order items
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OrderMenuItemResponseDto>> getOrderMenuItems(@PathVariable Long orderId) {
        List<OrderMenuItemResponseDto> items = orderMenuItemService.getOrderMenuItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }

    // ✅ Only customers can add items to their orders
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderMenuItemResponseDto> addMenuItemToOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderMenuItemRequestDto dto) {
        OrderMenuItemResponseDto created = orderMenuItemService.createOrderMenuItem(orderId, dto);
        return ResponseEntity.ok(created);
    }

    // ✅ Only customers can update order items
    @PutMapping("/{orderMenuItemId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderMenuItemResponseDto> updateMenuItemInOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderMenuItemId,
            @Valid @RequestBody OrderMenuItemRequestDto dto) {
        OrderMenuItemResponseDto updated = orderMenuItemService.updateOrderMenuItem(orderId, orderMenuItemId, dto);
        return ResponseEntity.ok(updated);
    }

    // ✅ Only customers can delete order items
    @DeleteMapping("/{orderMenuItemId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> deleteMenuItemFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderMenuItemId) {
        orderMenuItemService.deleteOrderMenuItem(orderId, orderMenuItemId);
        return ResponseEntity.noContent().build();
    }
}