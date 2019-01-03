package com.cargodelivery.repository;

public interface DistanceRepository {
    int getDistanceBetweenTwoCities(String firstCity, String secondCity);
}
