package com.hexa.example.orders.infra.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.entities.OrderDetails;
import com.hexa.example.orders.domain.exceptions.MinimumAcceptedAmountNotFulfilledException;
import com.hexa.example.orders.domain.ports.in.PlaceOrder;
import com.hexa.example.orders.infra.in.web.OrderDTO;
import com.hexa.example.orders.infra.in.web.OrdersRestAdapter;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = OrdersRestAdapter.class)
class OrdersRestAdapterTest {

  @MockBean PlaceOrder placeOrder;

  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @Test
  void should_place_order_when_order_is_higher_than_minimum_total_value() throws Exception {
    // given
    var orderDTO = new OrderDTO(UUID.fromString("5aa22f7b-04d4-4900-b563-05c6f2dea0aa"), 60);

    var customerId = orderDTO.customerId();
    var total = new Money(orderDTO.total());
    var order = new Order(new OrderDetails(customerId, total));

    given(placeOrder.apply(any(Order.class))).willReturn(order);

    mockMvc
        .perform(
            post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customerId").value("5aa22f7b-04d4-4900-b563-05c6f2dea0aa"))
        .andExpect(jsonPath("$.total").value(60));
  }

  @Test
  void should_return_bad_request_when_order_total_is_less_than_minimum_total_value()
      throws Exception {
    // given
    var orderDTO = new OrderDTO(UUID.fromString("5aa22f7b-04d4-4900-b563-05c6f2dea0aa"), 60);

    var customerId = orderDTO.customerId();
    var total = new Money(orderDTO.total());
    var order = new Order(new OrderDetails(customerId, total));

    given(placeOrder.apply(any(Order.class)))
        .willThrow(new MinimumAcceptedAmountNotFulfilledException(order, new Money(100)));

    mockMvc
        .perform(
            post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(
            content()
                .string(
                    "error: minimum amount condition not fullfilled order total 60.0 minimum accepted amount 100.0"));
  }
}
