package com.hexa.example.orders.infra.in.web;

import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.entities.OrderDetails;

public class OrderDTOMapper {

  private OrderDTOMapper() {}

  public static OrderDTO fromDomain(Order order) {
    return new OrderDTO(order.getDetails().customerId(), order.getTotal().amount().doubleValue());
  }

  public static Order toDomain(OrderDTO orderDTO) {
    var customerId = orderDTO.customerId();
    var total = new Money(orderDTO.total());
    return new Order(new OrderDetails(customerId, total));
  }
}
