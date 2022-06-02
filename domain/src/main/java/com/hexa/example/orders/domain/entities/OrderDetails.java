package com.hexa.example.orders.domain.entities;

import java.util.UUID;

public record OrderDetails(UUID customerId, Money orderTotal) {
}
