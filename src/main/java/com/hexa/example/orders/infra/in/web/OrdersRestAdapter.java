package com.hexa.example.orders.infra.in.web;

import com.hexa.example.orders.domain.exceptions.MinimumAcceptedAmountNotFulfilledException;
import com.hexa.example.orders.domain.ports.in.PlaceOrder;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersRestAdapter {

  private final PlaceOrder placeOrder;

  public OrdersRestAdapter(PlaceOrder placeOrder) {
    this.placeOrder = placeOrder;
  }

  @PostMapping
  OrderDTO post(@RequestBody OrderDTO orderDTO) {
    return Optional.of(orderDTO)
        .map(OrderDTOMapper::toDomain)
        .map(placeOrder::apply)
        .map(OrderDTOMapper::fromDomain)
        .get();
  }

  @ExceptionHandler({
    MinimumAcceptedAmountNotFulfilledException.class,
  })
  public ResponseEntity<String> handleException(
      MinimumAcceptedAmountNotFulfilledException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error: " + exception.getMessage());
  }
}
