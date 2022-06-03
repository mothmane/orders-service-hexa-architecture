package com.hexa.example.orders;

import com.hexa.example.orders.domain.ports.out.Orders;
import com.hexa.example.orders.infra.out.repositories.OrdersDatabaseAdapter;
import com.hexa.example.orders.infra.out.repositories.SpringOrdersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraConfiguration {

  @Bean
  public Orders orders(SpringOrdersRepository springOrdersRepository) {
    return new OrdersDatabaseAdapter(springOrdersRepository);
  }
}
