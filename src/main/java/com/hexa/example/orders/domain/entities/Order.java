package com.hexa.example.orders.domain.entities;

import static com.hexa.example.orders.domain.entities.OrderState.APPROVED;
import static com.hexa.example.orders.domain.entities.OrderState.PENDING;
import static com.hexa.example.orders.domain.entities.OrderState.REJECTED;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Order implements Serializable {

  private UUID id = UUID.randomUUID();

  private OrderState state;

  private OrderDetails details;

  public Order(OrderDetails details) {
    this.details = details;
    this.state = PENDING;
  }

  public Order(OrderDetails details, OrderState state) {
    this.details = details;
    this.state = state;
  }

  public Order(UUID id, OrderDetails details, OrderState state) {
    this.id = id;
    this.details = details;
    this.state = state;
  }

  public Order approve() {
    this.setState(APPROVED);
    return this;
  }

  public Order reject() {
    this.setState(REJECTED);
    return this;
  }

  public Money getTotal() {
    return this.details.orderTotal();
  }

  public UUID getId() {
    return id;
  }

  public OrderDetails getDetails() {
    return details;
  }

  public OrderState getState() {
    return state;
  }

  public void setState(OrderState state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(id, order.id)
        && state == order.state
        && Objects.equals(details, order.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, state, details);
  }

  @Override
  public String toString() {
    return "Order{" + "id=" + id + ", state=" + state + ", details=" + details + '}';
  }
}
