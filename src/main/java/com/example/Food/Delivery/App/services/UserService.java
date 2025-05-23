package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.entities.DeliveryDriver;
import com.example.Food.Delivery.App.repositories.UserRepository;
import com.example.Food.Delivery.App.dtos.Register.RegisterRequestDto;
import com.example.Food.Delivery.App.entities.User;
import com.example.Food.Delivery.App.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole() != null ? request.getRole() : UserRole.CUSTOMER
        );

        user.setPhoneNumber(request.getPhoneNumber());

        if (user.getRole() == UserRole.DRIVER) {
            DeliveryDriver driver = new DeliveryDriver();
            driver.setFirstName(request.getFirstName());
            driver.setLastName(request.getLastName());
            driver.setEmail(request.getEmail());
            driver.setPhoneNumber(request.getPhoneNumber());
            driver.setUser(user);
            user.setDeliveryDriver(driver);
        }
        return userRepository.save(user);
    }
}
