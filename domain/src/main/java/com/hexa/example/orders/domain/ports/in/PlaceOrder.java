package com.hexa.example.orders.domain.ports.in;

import com.hexa.example.orders.domain.entities.Order;
import java.util.function.UnaryOperator;

public interface PlaceOrder extends UnaryOperator<Order> {}
