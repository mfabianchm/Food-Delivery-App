package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemResponseDto;
import com.example.Food.Delivery.App.entities.MenuItem;
import com.example.Food.Delivery.App.entities.Restaurant;
import com.example.Food.Delivery.App.repositories.MenuItemRepository;
import com.example.Food.Delivery.App.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItemResponseDto createMenuItem(MenuItemRequestDto dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        MenuItem menuItem = new MenuItem(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                restaurant
        );

        MenuItem saved = menuItemRepository.save(menuItem);
        return toResponseDto(saved);
    }

    public MenuItemResponseDto getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));

        return toResponseDto(menuItem);
    }

    public List<MenuItemResponseDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto dto) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setRestaurant(restaurant);

        return toResponseDto(menuItemRepository.save(existing));
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem not found with id " + id);
        }
        menuItemRepository.deleteById(id);
    }

    private MenuItemResponseDto toResponseDto(MenuItem item) {
        return new MenuItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getRestaurant().getId(),
                item.getRestaurant().getName()
        );
    }
}
