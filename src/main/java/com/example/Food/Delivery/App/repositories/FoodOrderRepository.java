package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Country;
import com.example.Food.Delivery.App.entities.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
