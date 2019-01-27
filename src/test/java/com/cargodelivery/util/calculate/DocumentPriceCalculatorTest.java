package com.cargodelivery.util.calculate;

import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentPriceCalculatorTest {

    @Test
    public void getPrice() {
        String cargoType = "DOCUMENT";
        String weight = "1";
        int distance = 100;
        DocumentPriceCalculator calculator = new DocumentPriceCalculator();
        int price = calculator.getPrice(cargoType, weight, distance);
        int expectedPrice = 40;
        assertEquals(expectedPrice, price);
    }
}