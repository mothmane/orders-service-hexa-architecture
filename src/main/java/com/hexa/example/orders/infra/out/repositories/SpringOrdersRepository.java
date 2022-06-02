package com.hexa.example.orders.infra.out.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface SpringOrdersRepository extends CrudRepository<OrderEntity, UUID> {}
