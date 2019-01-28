package com.cargodelivery.service.impl;

import com.cargodelivery.repository.DistanceRepository;
import com.cargodelivery.repository.impl.MySQLDistanceRepository;
import com.cargodelivery.service.DistanceService;

public class DistanceServiceImpl implements DistanceService {

    private DistanceRepository distanceRepository;

    public DistanceServiceImpl() {
        this.distanceRepository = new MySQLDistanceRepository();
    }

    public DistanceServiceImpl(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    @Override
    public synchronized int getDistanceBetweenTwoCities(String firstCity, String secondCity) {
        return distanceRepository.getDistanceBetweenTwoCities(firstCity, secondCity);
    }

    @Override
    public synchronized int getDeliveryTermBetweenTwoCities(String firstCity, String secondCity) {
        return distanceRepository.getDeliveryTermBetweenTwoCities(firstCity, secondCity);
    }
}
