package com.hexa.example.orders.infra.out;

import com.hexa.example.orders.domain.entities.Money;
import java.math.BigDecimal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "order.minimum.accepted")
public class OrdersSettingsConfigurationFileAdapter implements com.hexa.example.orders.domain.ports.out.OrdersSettings {

  private Money minimumAcceptedAmount;

  @Override
  public Money getMinimumAcceptedAmount() {
    return this.minimumAcceptedAmount;
  }

  public void setAmount(BigDecimal amount) {
    this.minimumAcceptedAmount = new Money(amount);
  }
}
