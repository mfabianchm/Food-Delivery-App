package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemRequestDto;
import com.example.Food.Delivery.App.dtos.MenuItem.MenuItemResponseDto;
import com.example.Food.Delivery.App.services.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MenuItemResponseDto> createMenuItem(@Valid @RequestBody MenuItemRequestDto dto) {
        MenuItemResponseDto created = menuItemService.createMenuItem(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> getMenuItem(@PathVariable Long id) {
        MenuItemResponseDto item = menuItemService.getMenuItemById(id);
        return ResponseEntity.ok(item);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<MenuItemResponseDto>> getAllMenuItems() {
        List<MenuItemResponseDto> items = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(items);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequestDto dto) {
        MenuItemResponseDto updated = menuItemService.updateMenuItem(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
