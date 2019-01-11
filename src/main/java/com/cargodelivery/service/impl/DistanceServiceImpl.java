package com.cargodelivery.service.impl;

import com.cargodelivery.repository.DistanceRepository;
import com.cargodelivery.repository.impl.DistanceRepositoryImpl;
import com.cargodelivery.service.DistanceService;

import java.sql.Connection;

public class DistanceServiceImpl implements DistanceService {

    private DistanceRepository distanceRepository;

    public DistanceServiceImpl(Connection connection) {
        this.distanceRepository = new DistanceRepositoryImpl(connection);
    }


    @Override
    public int getDistanceBetweenTwoCities(String firstCity, String secondCity) {
        return distanceRepository.getDistanceBetweenTwoCities(firstCity, secondCity);
    }

    @Override
    public int getDeliveryTermBetweenTwoCities(String firstCity, String secondCity) {
        return distanceRepository.getDeliveryTermBetweenTwoCities(firstCity, firstCity);
    }
}
