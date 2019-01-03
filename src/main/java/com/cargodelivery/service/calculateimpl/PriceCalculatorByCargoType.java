package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;

import java.sql.Connection;

public class PriceCalculatorByCargoType {

    private PriceCalculatorFactory priceCalculatorFactory;

    public PriceCalculatorByCargoType(Connection connection) {
        this.priceCalculatorFactory = new PriceCalculatorFactory(connection);
    }

    public int getPriceExecuter(Order order) {
        String typeName = order.getType().name();
        CalculatorStrategy calculatorStrategy = priceCalculatorFactory.createCalculator(typeName);
        return calculatorStrategy.getPrice(order);
    }
}


