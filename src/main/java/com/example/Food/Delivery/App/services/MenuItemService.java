package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemResponseDto;
import com.example.Food.Delivery.App.entities.MenuItem;
import com.example.Food.Delivery.App.entities.Restaurant;
import com.example.Food.Delivery.App.mappers.MenuItemMapper;
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

    private final MenuItemMapper menuItemMapper;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository, MenuItemMapper menuItemMapper  ) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemMapper = menuItemMapper;
    }

    public MenuItemResponseDto createMenuItem(MenuItemRequestDto dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        MenuItem menuItem = menuItemMapper.toEntity(dto, restaurant);

        return menuItemMapper.toDto(menuItemRepository.save(menuItem));
    }

    public MenuItemResponseDto getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));

        return menuItemMapper.toDto(menuItem);
    }

    public List<MenuItemResponseDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto dto) {

        MenuItem existingMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        existingMenuItem.setName(dto.getName());
        existingMenuItem.setDescription(dto.getDescription());
        existingMenuItem.setPrice(dto.getPrice());
        existingMenuItem.setRestaurant(restaurant);

        return menuItemMapper.toDto(menuItemRepository.save(existingMenuItem));

    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem not found with id " + id);
        }
        menuItemRepository.deleteById(id);
    }


}
