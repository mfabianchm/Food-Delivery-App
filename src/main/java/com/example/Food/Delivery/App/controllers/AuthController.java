package com.example.Food.Delivery.App.controllers;

import com.example.Food.Delivery.App.dtos.Authentication.AuthenticationRequestDto;
import com.example.Food.Delivery.App.dtos.Authentication.AuthenticationResponse;
import com.example.Food.Delivery.App.dtos.Register.RegisterRequestDto;
import com.example.Food.Delivery.App.entities.User;
import com.example.Food.Delivery.App.services.AuthenticationService;
import com.example.Food.Delivery.App.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequestDto authRequest) {
        String jwt = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto request) {
        User createdUser = userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully: " + createdUser.getUsername());
    }
}
