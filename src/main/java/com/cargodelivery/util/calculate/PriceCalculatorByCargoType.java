package com.cargodelivery.util.calculate;

public class PriceCalculatorByCargoType {

    private PriceCalculatorFactory priceCalculatorFactory;

    public PriceCalculatorByCargoType(PriceCalculatorFactory priceCalculatorFactory) {
        this.priceCalculatorFactory = priceCalculatorFactory;
    }

    public int getPriceExecuter(String cargoType, String weight, int distance) {
        CalculatorStrategy calculatorStrategy = priceCalculatorFactory.createCalculator(cargoType);
        return calculatorStrategy.getPrice(cargoType, weight, distance);
    }
}


