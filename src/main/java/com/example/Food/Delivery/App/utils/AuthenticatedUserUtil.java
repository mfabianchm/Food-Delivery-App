package com.example.Food.Delivery.App.utils;

import com.example.Food.Delivery.App.entities.DeliveryDriver;
import com.example.Food.Delivery.App.entities.User;
import com.example.Food.Delivery.App.repositories.DeliveryDriverRepository;
import com.example.Food.Delivery.App.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserUtil {
    private final UserRepository userRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;

    public AuthenticatedUserUtil(UserRepository userRepository, DeliveryDriverRepository deliveryDriverRepository) {
        this.userRepository = userRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public DeliveryDriver getAuthenticatedDeliveryDriver() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return deliveryDriverRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("DeliveryDriver not found for user: " + username));
    }
}
