package com.hexa.example.orders.domain.usecases;

import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.exceptions.MinimumAcceptedAmountNotFulfilledException;
import com.hexa.example.orders.domain.ports.in.PlaceOrder;
import com.hexa.example.orders.domain.ports.out.Orders;
import com.hexa.example.orders.domain.ports.out.OrdersSettings;


public class PlaceOrderUseCase implements PlaceOrder {

  private final OrdersSettings ordersConfiguration;
  private final Orders orders;

  public PlaceOrderUseCase(OrdersSettings ordersConfiguration, Orders orders) {
    this.ordersConfiguration = ordersConfiguration;
    this.orders = orders;
  }

  public Order apply(Order order) {

    Money minimumAcceptedAmount = ordersConfiguration.getMinimumAcceptedAmount();

    if (order.getTotal().isGreaterThanOrEqual(minimumAcceptedAmount)) {
      order.approve();
      return orders.save(order);
    }

    order.reject();
    throw new MinimumAcceptedAmountNotFulfilledException(order, minimumAcceptedAmount);
  }
}
