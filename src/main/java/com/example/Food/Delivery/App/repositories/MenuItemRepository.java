package com.example.Food.Delivery.App.repositories;

import com.example.Food.Delivery.App.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
