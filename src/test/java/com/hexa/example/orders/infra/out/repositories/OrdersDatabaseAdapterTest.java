package com.hexa.example.orders.infra.out.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.hexa.example.orders.domain.entities.Money;
import com.hexa.example.orders.domain.entities.Order;
import com.hexa.example.orders.domain.entities.OrderDetails;
import com.hexa.example.orders.domain.entities.OrderState;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(OrdersDatabaseAdapterTest.RepositoryConfig.class)
class OrdersDatabaseAdapterTest {

  @Autowired
  OrdersDatabaseAdapter ordersDatabaseAdapter;

  @Autowired SpringOrdersRepository springOrdersRepository;

  @Test
  void should_save_Order() {
    // given
    var customerId = UUID.randomUUID();
    var total = new Money(60.00);
    var order = new Order(new OrderDetails(customerId, total), OrderState.APPROVED);

    // when
    var storedOrder = ordersDatabaseAdapter.save(order);
    // then
    var expected = new OrderEntity(storedOrder.getId(), customerId, 60);

    var actual = springOrdersRepository.findById(storedOrder.getId()).get();

    assertThat(actual).isEqualTo(expected);
  }

  @TestConfiguration
  public static class RepositoryConfig {

    @Bean
    public OrdersDatabaseAdapter ordersRepository(SpringOrdersRepository springOrdersRepository) {
      return new OrdersDatabaseAdapter(springOrdersRepository);
    }
  }
}
