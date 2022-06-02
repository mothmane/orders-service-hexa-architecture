package com.hexa.example.orders.domain.exceptions;

import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;

public class MinimumAcceptedAmountNotFulfilledException extends RuntimeException {

  private final Order rejectedOrder;
  private final Money minimumAcceptedAmount;

  public MinimumAcceptedAmountNotFulfilledException(
      Order rejectedOrder, Money minimumAcceptedAmount) {

    super(
        "minimum amount condition not fullfilled order total "
            + rejectedOrder.getTotal().amount()
            + " minimum accepted amount "
            + minimumAcceptedAmount.amount());

    this.rejectedOrder = rejectedOrder;
    this.minimumAcceptedAmount = minimumAcceptedAmount;
  }

  public Order getRejectedOrder() {
    return rejectedOrder;
  }

  public Money getMinimumAcceptedAmount() {
    return minimumAcceptedAmount;
  }
}
