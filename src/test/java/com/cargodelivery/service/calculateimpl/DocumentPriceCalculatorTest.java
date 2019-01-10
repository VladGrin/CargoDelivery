package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentPriceCalculatorTest {

    @Test
    public void getPrice() {
        Order order = new Order.OrderBuilder().build();
        DocumentPriceCalculator calculator = new DocumentPriceCalculator();
        int price = calculator.getPrice(order);
        int expectedPrice = 40;
        assertEquals(expectedPrice, price);
    }
}