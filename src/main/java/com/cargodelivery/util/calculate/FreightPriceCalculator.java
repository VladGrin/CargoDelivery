package com.cargodelivery.util.calculate;

import com.cargodelivery.util.CalculatorStrategy;

class FreightPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice(String cargoType, String weight, int distance) {
        return (int) (1000 + distance * 0.8 * Integer.parseInt(weight) / 1000);
    }
}
