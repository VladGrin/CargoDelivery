package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;

class DocumentPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice(Order order) {
        return 40;
    }
}
