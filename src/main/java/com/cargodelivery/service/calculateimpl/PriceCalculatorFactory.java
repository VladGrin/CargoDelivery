package com.cargodelivery.service.calculateimpl;

import java.sql.Connection;

class PriceCalculatorFactory {

    private Connection connection;

    public PriceCalculatorFactory(Connection connection) {
        this.connection = connection;
    }

    CalculatorStrategy createCalculator(String cargo) {
        switch (cargo) {
            case "DOCUMENT":
                return new DocumentPriceCalculator();
            case "PARSEL":
                return new ParselPriceCalculator(connection);
            case "FREIGHT":
                return new FreightPriceCalculator(connection);
            default:
                throw new RuntimeException(cargo + " - unknown cargo.");
        }
    }
}
