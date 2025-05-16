package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.Restaurant;
import com.example.Food.Delivery.App.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
