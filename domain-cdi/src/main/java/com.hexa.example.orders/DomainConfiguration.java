package com.hexa.example.orders;

import com.hexa.example.orders.domain.ports.in.PlaceOrderUseC;
import com.hexa.example.orders.domain.ports.out.Orders;
import com.hexa.example.orders.domain.ports.out.OrdersSettings;
import com.hexa.example.orders.domain.usecases.PlaceOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public PlaceOrder placeOrder(OrdersSettings ordersSettings, Orders orders) {
    return new PlaceOrderUseCase(ordersSettings, orders);
  }
}
