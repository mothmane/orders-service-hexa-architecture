package com.hexa.example.orders.domain.entities;

import java.math.BigDecimal;


public record Money(BigDecimal amount) {

    public Money(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

}
