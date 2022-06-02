package com.hexa.example.orders.infra.in.web;

import java.util.UUID;

public record OrderDTO(UUID customerId, double total) {

}
