package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.FoodOrder;
import com.example.Food.Delivery.App.entities.OrderMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, Long> {
    List<OrderMenuItem> findByFoodOrder(FoodOrder foodOrder);
}
