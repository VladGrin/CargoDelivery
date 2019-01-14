package com.cargodelivery.util.calculate;

import com.cargodelivery.util.CalculatorStrategy;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriceCalculatorFactoryTest {

    @Test
    public void whenDocumentThenReturnDocumentPriceCalculator() {
        PriceCalculatorFactory factory = new PriceCalculatorFactory();
        CalculatorStrategy calculatorStrategy = factory.createCalculator("DOCUMENT");
        assertTrue(calculatorStrategy instanceof DocumentPriceCalculator);
    }

    @Test
    public void whenParselThenReturnParselPriceCalculator() {
        PriceCalculatorFactory factory = new PriceCalculatorFactory();
        CalculatorStrategy calculatorStrategy = factory.createCalculator("PARSEL");
        assertTrue(calculatorStrategy instanceof ParselPriceCalculator);
    }

    @Test
    public void whenFreightThenReturnParselPriceCalculator() {
        PriceCalculatorFactory factory = new PriceCalculatorFactory();
        CalculatorStrategy calculatorStrategy = factory.createCalculator("FREIGHT");
        assertTrue(calculatorStrategy instanceof FreightPriceCalculator);
    }

    @Test(expected = RuntimeException.class)
    public void whenAnythingElseThenReturnRuntimeException() {
        PriceCalculatorFactory factory = new PriceCalculatorFactory();
        factory.createCalculator("SOMETHING");
    }

}