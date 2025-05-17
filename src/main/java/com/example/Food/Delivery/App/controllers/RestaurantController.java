package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.RestaurantRequestDto;
import com.example.Food.Delivery.App.dtos.RestaurantResponseDto;
import com.example.Food.Delivery.App.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // 1. Public: List all restaurants
    @GetMapping
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

//     2. Admin: Add new restaurant
    //@Valid annotationRuns validation annotations (e.g., @NotNull, @Size) defined in the DTO.
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody @Valid RestaurantRequestDto dto) {
        RestaurantResponseDto created = restaurantService.createRestaurant(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

//    2. Admin: Update existing restaurant
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(
            @PathVariable Long id,
            @RequestBody @Valid RestaurantRequestDto dto) {
        RestaurantResponseDto updated = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.ok(updated);
    }
}
