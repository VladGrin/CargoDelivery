package com.cargodelivery.util.calculate;

import com.cargodelivery.util.CalculatorStrategy;

class DocumentPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice(String cargoType, String weight, int distance) {
        return 40;
    }
}
