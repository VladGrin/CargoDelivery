package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;

public interface CalculatorStrategy {
    int getPrice(Order order);
}
