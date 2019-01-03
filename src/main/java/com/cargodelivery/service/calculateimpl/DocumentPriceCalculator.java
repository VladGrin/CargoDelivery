package com.cargodelivery.service.calculateimpl;

class DocumentPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice() {
        return 40;
    }
}
