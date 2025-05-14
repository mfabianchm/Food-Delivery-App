package com.example.Food.Delivery.App.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

    private String apartmentNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAddress> userAddresses = new ArrayList<>();

}
