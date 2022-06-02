package com.hexa.example.orders.domain.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.entities.OrderDetails;
import com.hexa.example.orders.domain.entities.OrderState;
import com.hexa.example.orders.domain.exceptions.MinimumAcceptedAmountNotFulfilledException;
import com.hexa.example.orders.domain.ports.out.Orders;
import com.hexa.example.orders.domain.ports.out.OrdersSettings;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlaceOrderUseCaseTest {

  @Mock
  OrdersSettings ordersSettings;
  @Mock
  Orders orders;

  @InjectMocks PlaceOrderUseCase placeOrder;

  @Test
  void should_place_order_when_order_is_higher_than_minimum_total_value() {
    // given
    var customerId = UUID.randomUUID();
    var total = new Money(60.00);
    var order = new Order(new OrderDetails(customerId, total), OrderState.APPROVED);

    given(ordersSettings.getMinimumAcceptedAmount()).willReturn(new Money(50.00));
    given(orders.save(any(Order.class))).willReturn(order);

    // when
    var actual = placeOrder.apply(order);

    // then
    var expected =
        new Order(order.getId(), new OrderDetails(customerId, total), OrderState.APPROVED);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void should_throw_exception__when_order_total_is_less_than_minimum_total_value() {
    // given
    var customerId = UUID.randomUUID();
    var total = new Money(40.00);

    var order = new Order(new OrderDetails(customerId, total));

    given(ordersSettings.getMinimumAcceptedAmount()).willReturn(new Money(50.00));

    // when

    assertThatExceptionOfType(MinimumAcceptedAmountNotFulfilledException.class)
        .isThrownBy(
            () -> {
              placeOrder.apply(order);
            })
        .withMessage(
            "minimum amount condition not fullfilled order total 40.0 minimum accepted amount 50.0");
  }
}
