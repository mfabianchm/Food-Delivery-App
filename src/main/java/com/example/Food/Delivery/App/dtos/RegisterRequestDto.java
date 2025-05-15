package com.example.Food.Delivery.App.dtos;

import com.example.Food.Delivery.App.enums.UserRole;
import jakarta.validation.constraints.*;

public class RegisterRequestDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String fullName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull
    private UserRole role;

    @Pattern(regexp = "^\\+?[0-9]{10}$", message = "Invalid phone number format")
    private String phoneNumber;

    // Constructors
    public RegisterRequestDto() {}

    public RegisterRequestDto(String fullName, String email, String username, String password, UserRole role, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
