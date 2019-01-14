package com.cargodelivery.util.calculate;

import com.cargodelivery.util.CalculatorStrategy;

public class PriceCalculatorFactory {

    CalculatorStrategy createCalculator(String cargo) {
        switch (cargo) {
            case "DOCUMENT":
                return new DocumentPriceCalculator();
            case "PARSEL":
                return new ParselPriceCalculator();
            case "FREIGHT":
                return new FreightPriceCalculator();
            default:
                throw new RuntimeException(cargo + " - unknown cargo.");
        }
    }
}
