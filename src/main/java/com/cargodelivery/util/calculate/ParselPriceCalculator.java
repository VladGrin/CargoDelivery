package com.cargodelivery.util.calculate;

class ParselPriceCalculator implements CalculatorStrategy {

    @Override
    public int getPrice(String cargoType, String weight, int distance) {
        return (int) (40 + distance * 0.01 * Integer.parseInt(weight));
    }
}
