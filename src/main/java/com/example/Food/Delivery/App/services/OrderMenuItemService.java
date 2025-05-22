package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.OrderMenuItem.OrderMenuItemResponseDto;
import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.MenuItem;
import com.example.Food.Delivery.App.entities.OrderMenuItem;
import com.example.Food.Delivery.App.exceptions.ResourceNotFoundException;
import com.example.Food.Delivery.App.mappers.OrderMenuItemMapper;
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

    private final OrderMenuItemMapper orderMenuItemMapper;

    public OrderMenuItemService(OrderMenuItemRepository orderMenuItemRepository,
                                FoodOrderRepository foodOrderRepository,
                                MenuItemRepository menuItemRepository,
                                OrderMenuItemMapper orderMenuItemMapper) {
        this.orderMenuItemRepository = orderMenuItemRepository;
        this.foodOrderRepository = foodOrderRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderMenuItemMapper = orderMenuItemMapper;
    }

    // List all items for an order
    public List<OrderMenuItemResponseDto> getOrderMenuItemsByOrderId(Long orderId) {
        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        List<OrderMenuItem> items = orderMenuItemRepository.findByFoodOrder(order);


        return items.stream()
                .map(orderMenuItemMapper::toDto)
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
        return orderMenuItemMapper.toDto(saved);
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

        existingItem.setQuantity_ordered(dto.getQuantityOrdered());
        OrderMenuItem updated = orderMenuItemRepository.save(existingItem);
        return orderMenuItemMapper.toDto(updated);
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

}
