package com.example.Food.Delivery.App.dtos.OrderStatus;

import com.example.Food.Delivery.App.enums.OrderStatusType;
import jakarta.validation.constraints.NotNull;

public class ChangeOrderStatusRequestDto {
    @NotNull(message = "Status must not be null")
    private OrderStatusType newStatus;

    public OrderStatusType getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(OrderStatusType newStatus) {
        this.newStatus = newStatus;
    }
}
