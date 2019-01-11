package com.cargodelivery.util.calculate;

class DocumentPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice(String cargoType, String weight, int distance) {
        return 40;
    }
}
