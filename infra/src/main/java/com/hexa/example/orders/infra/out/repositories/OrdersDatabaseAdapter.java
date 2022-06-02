package com.hexa.example.orders.infra.out.repositories;

import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.ports.out.Orders;
import java.util.Optional;

public class OrdersDatabaseAdapter implements Orders {

  private SpringOrdersRepository springOrdersRepository;

  public OrdersDatabaseAdapter(SpringOrdersRepository springOrdersRepository) {
    this.springOrdersRepository = springOrdersRepository;
  }

  @Override
  public Order save(Order order) {
    return Optional.of(order)
        .map(OrderEntityMapper::fromDomain)
        .map(springOrdersRepository::save)
        .map(OrderEntityMapper::toDomain)
        .get();
  }
}
