package com.cargodelivery.service;

public interface DistanceService {
    int getDistanceBetweenTwoCities(String firstCity, String secondCity);

    int getDeliveryTermBetweenTwoCities(String firstCity, String secondCity);
}
