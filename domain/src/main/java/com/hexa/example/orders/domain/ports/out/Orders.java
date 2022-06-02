package com.hexa.example.orders.domain.ports.out;

import com.hexa.example.orders.domain.entities.Order;

public interface Orders {

  Order save(Order order);
}
