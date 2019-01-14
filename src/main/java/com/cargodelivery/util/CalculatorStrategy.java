package com.cargodelivery.util;

public interface CalculatorStrategy {

    int getPrice(String cargoType, String weight, int distance);
}
