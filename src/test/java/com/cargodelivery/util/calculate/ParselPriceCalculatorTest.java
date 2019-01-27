package com.cargodelivery.util.calculate;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParselPriceCalculatorTest {

    @Test
    public void getPrice() {
        String cargoType = "DOCUMENT";
        String weight = "2";
        int distance = 500;
        ParselPriceCalculator calculator = new ParselPriceCalculator();
        int price = calculator.getPrice(cargoType, weight, distance);
        int expectedPrice = 50;
        assertEquals(expectedPrice, price);
    }
}