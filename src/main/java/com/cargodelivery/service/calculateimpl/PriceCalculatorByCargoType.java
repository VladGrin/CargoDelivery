package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;
import com.cargodelivery.service.CalculateServise;

public class PriceCalculatorByCargoType {
    private CalculatorStrategy calculatorStrategy;

    public PriceCalculatorByCargoType(Order order) {
        String typeName = order.getType().name();
        this.calculatorStrategy = new PriceCalculatorFactory().createCalculator(typeName);
    }

    public int getPriceExecuter() {
        return calculatorStrategy.getPrice();
    }
}


