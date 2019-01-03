package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;
import com.cargodelivery.service.CalculateServise;

public class PriceCalculatorByCargoType {

    public int getPriceExecuter(Order order) {
        String typeName = order.getType().name();
        CalculatorStrategy calculatorStrategy = new PriceCalculatorFactory().createCalculator(typeName);
        return calculatorStrategy.getPrice(order);
    }
}


