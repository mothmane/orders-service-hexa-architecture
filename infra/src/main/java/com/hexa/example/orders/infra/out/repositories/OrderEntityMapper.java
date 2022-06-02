package com.hexa.example.orders.infra.out.repositories;

import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.entities.OrderDetails;

public class OrderEntityMapper {

  private OrderEntityMapper() {}

  public static OrderEntity fromDomain(Order order) {
    return new OrderEntity(
        order.getId(), order.getDetails().customerId(), order.getTotal().amount().doubleValue());
  }

  public static Order toDomain(OrderEntity orderEntity) {
    var orderDetails =
        new OrderDetails(orderEntity.getCustomerId(), new Money(orderEntity.getTotal()));
    return new Order(orderEntity.getId(), orderDetails, map(orderEntity.getState()));
  }

  private static com.hexa.example.orders.domain.entities.OrderState map(OrderState state) {
    return com.hexa.example.orders.domain.entities.OrderState.valueOf(state.toString());
  }
}
