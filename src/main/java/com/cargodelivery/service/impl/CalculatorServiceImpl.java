package com.cargodelivery.service.impl;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.service.CalculateServise;
import com.cargodelivery.util.calculate.PriceCalculatorByCargoType;
import com.cargodelivery.validator.Validator;

public class CalculatorServiceImpl implements CalculateServise {

    private PriceCalculatorByCargoType calculator;

    public CalculatorServiceImpl(PriceCalculatorByCargoType calculator) {
        this.calculator = calculator;
    }

//    public CalculatorServiceImpl() {
//        this.calculator = new PriceCalculatorByCargoType();
//    }

    @Override
    public int getOrderPrice(String cargoType, String weight, int distance) throws IncorrectInputException {
        Validator.validateWeight(weight, cargoType);
        Validator.validateText(cargoType);

        return calculator.getPriceExecuter(cargoType, weight, distance);
    }
}
