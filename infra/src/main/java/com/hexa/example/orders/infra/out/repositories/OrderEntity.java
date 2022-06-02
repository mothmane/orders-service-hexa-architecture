package com.hexa.example.orders.infra.out.repositories;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {

  @Id private UUID id;

  private UUID customerId;

  private double total;

  @Enumerated(EnumType.STRING)
  private OrderState state = OrderState.PENDING;

  public OrderEntity() {}

  public OrderEntity(UUID id, UUID customerId, double total) {
    this.id = id;
    this.customerId = customerId;
    this.total = total;
  }

  public OrderEntity(UUID id, UUID customerId, double total, OrderState state) {
    this.id = id;
    this.customerId = customerId;
    this.total = total;
    this.state = state;
  }

  public UUID getId() {
    return id;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public double getTotal() {
    return total;
  }

  public OrderState getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderEntity that = (OrderEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(customerId, that.customerId)
        && Objects.equals(total, that.total)
        && state == that.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerId, total, state);
  }

  @Override
  public String toString() {
    return "OrderEntity{"
        + "id="
        + id
        + ", customerId="
        + customerId
        + ", total="
        + total
        + ", state="
        + state
        + '}';
  }
}
