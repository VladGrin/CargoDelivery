package com.cargodelivery.util.calculate;

public interface CalculatorStrategy {

    int getPrice(String cargoType, String weight, int distance);
}
