package com.hexa.example.orders.domain.ports.out;

import com.hexa.example.orders.domain.entities.Money;

public interface OrdersSettings {

  Money getMinimumAcceptedAmount();
}
