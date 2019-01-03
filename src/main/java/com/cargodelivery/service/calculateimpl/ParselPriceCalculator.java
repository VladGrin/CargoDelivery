package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;
import com.cargodelivery.repository.DistanceRepository;
import com.cargodelivery.repository.impl.DistanceRepositoryImpl;

class ParselPriceCalculator implements CalculatorStrategy {

    private final DistanceRepository distanceRepository = new DistanceRepositoryImpl();

    @Override
    public int getPrice(Order order) {
        int distance = distanceRepository.getDistanceBetweenTwoCities(order.getCityFrom(), order.getCityTo());
        return (int) (40 + distance * 0.01 * order.getWeight());
    }
}
