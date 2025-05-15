package com.example.Food.Delivery.App.services;

import com.example.Food.Delivery.App.UserRepository.UserRepository;
import com.example.Food.Delivery.App.dtos.RegisterRequestDto;
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
                request.getFullName(),
                request.getEmail(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole() != null ? request.getRole() : UserRole.CUSTOMER
        );

        user.setPhoneNumber(request.getPhoneNumber());
        return userRepository.save(user);
    }
}
