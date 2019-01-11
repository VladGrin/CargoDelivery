package com.cargodelivery.service;

import com.cargodelivery.exception.IncorrectInputException;

public interface CalculateServise {
    int getOrderPrice(String cargoType, String weight, int distance) throws IncorrectInputException;
}
