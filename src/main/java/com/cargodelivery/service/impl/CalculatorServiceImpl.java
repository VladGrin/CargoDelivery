package com.cargodelivery.service.impl;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.model.Order;
import com.cargodelivery.service.CalculateServise;
import com.cargodelivery.service.calculateimpl.PriceCalculatorByCargoType;
import com.cargodelivery.validator.Validator;

public class CalculatorServiceImpl implements CalculateServise {

    @Override
    public int getOrderPrice(String cityFrom, String cityTo, String cargoType, String weight) throws IncorrectInputException {

        Validator.validateWeight(weight, cargoType);
        Validator.validateNumber(cityFrom);
        Validator.validateNumber(cityTo);
        Validator.validateText(cargoType);

        Order order = new Order.OrderBuilder()
                .setCityFrom(cityFrom)
                .setCityTo(cityTo)
                .setTypeByName(cargoType)
                .setWeight(Integer.parseInt(weight)).build();

        return new PriceCalculatorByCargoType(order).getPriceExecuter();
    }
}
