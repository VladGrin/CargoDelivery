package com.cargodelivery.util.calculate;

import org.junit.Test;

import static org.junit.Assert.*;

public class FreightPriceCalculatorTest {

    @Test
    public void getPrice() {
        String cargoType = "DOCUMENT";
        String weight = "200";
        int distance = 500;
        FreightPriceCalculator calculator = new FreightPriceCalculator();
        int price = calculator.getPrice(cargoType, weight, distance);
        int expectedPrice = 1080;
        assertEquals(expectedPrice, price);
    }
}