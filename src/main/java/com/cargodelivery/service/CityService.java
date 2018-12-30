package com.cargodelivery.service;

import com.cargodelivery.model.City;

import java.util.Set;

public interface CityService {
    boolean saveCity(City city);
    City getCityBuIdById(Integer id);
    City getCityByNameByName(String name);
    Set<City> getAllCities();
}
