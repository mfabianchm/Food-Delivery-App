package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemResponseDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.MenuItem;
import com.example.Food.Delivery.App.entities.OrderMenuItem;
import com.example.Food.Delivery.App.exceptions.ResourceNotFoundException;
import com.example.Food.Delivery.App.repositories.FoodOrderRepository;
import com.example.Food.Delivery.App.repositories.MenuItemRepository;
import com.example.Food.Delivery.App.repositories.OrderMenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMenuItemService {

    private final OrderMenuItemRepository orderMenuItemRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderMenuItemService(OrderMenuItemRepository orderMenuItemRepository,
                                FoodOrderRepository foodOrderRepository,
                                MenuItemRepository menuItemRepository) {
        this.orderMenuItemRepository = orderMenuItemRepository;
        this.foodOrderRepository = foodOrderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    // List all items for an order
    public List<OrderMenuItemResponseDto> getOrderMenuItemsByOrderId(Long orderId) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        List<OrderMenuItem> items = orderMenuItemRepository.findByFoodOrder(order);
        return items.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // Add menu item to order
    public OrderMenuItemResponseDto createOrderMenuItem(Long orderId, OrderMenuItemRequestDto dto) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id " + dto.getMenuItemId()));

        OrderMenuItem orderMenuItem = new OrderMenuItem(order, menuItem, dto.getQuantityOrdered());
        OrderMenuItem saved = orderMenuItemRepository.save(orderMenuItem);
        return mapToResponseDto(saved);
    }

    // Update quantity of menu item in order
    public OrderMenuItemResponseDto updateOrderMenuItem(Long orderId, Long orderMenuItemId, OrderMenuItemRequestDto dto) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        OrderMenuItem existingItem = orderMenuItemRepository.findById(orderMenuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderMenuItem not found with id " + orderMenuItemId));

        if (!existingItem.getFoodOrder().getId().equals(order.getId())) {
            throw new IllegalArgumentException("OrderMenuItem does not belong to Order " + orderId);
        }

        // Optionally allow updating menuItem? Usually not, so only quantity:
        existingItem.setQuantity_ordered(dto.getQuantityOrdered());
        OrderMenuItem updated = orderMenuItemRepository.save(existingItem);
        return mapToResponseDto(updated);
    }

    // Delete an item from order
    public void deleteOrderMenuItem(Long orderId, Long orderMenuItemId) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        OrderMenuItem existingItem = orderMenuItemRepository.findById(orderMenuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderMenuItem not found with id " + orderMenuItemId));

        if (!existingItem.getFoodOrder().getId().equals(order.getId())) {
            throw new IllegalArgumentException("OrderMenuItem does not belong to Order " + orderId);
        }

        orderMenuItemRepository.delete(existingItem);
    }

    // Mapping method DTO <- Entity
    private OrderMenuItemResponseDto mapToResponseDto(OrderMenuItem orderMenuItem) {
        OrderMenuItemResponseDto dto = new OrderMenuItemResponseDto();
        dto.setId(orderMenuItem.getId());
        dto.setMenuItemName(orderMenuItem.getMenuItem().getName());
        dto.setQuantityOrdered(orderMenuItem.getQuantity_ordered());
        return dto;
    }
}
