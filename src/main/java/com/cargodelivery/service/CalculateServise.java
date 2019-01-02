package com.cargodelivery.service;

import com.cargodelivery.exception.IncorrectInputException;

public interface CalculateServise {
    int getOrderPrice(String cityFrom, String cityTo, String cargo, String weight) throws IncorrectInputException;
}
