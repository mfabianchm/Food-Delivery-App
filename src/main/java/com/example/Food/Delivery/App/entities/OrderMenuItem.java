package com.example.Food.Delivery.App.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_menu_items")
public class OrderMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food_order_id")
    private FoodOrder foodOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer quantity_ordered;
}
