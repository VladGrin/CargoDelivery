package com.cargodelivery.service.calculateimpl;

class FreightPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice() {
        return 2;
    }
}
