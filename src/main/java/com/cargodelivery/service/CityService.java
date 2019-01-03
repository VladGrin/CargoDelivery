package com.cargodelivery.service;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;

import java.util.Set;

public interface CityService {
    boolean saveCity(City city);
    City getCityById(Integer id) throws NoSuchDataException;
    City getCityByName(String name) throws NoSuchDataException;
    Set<City> getAllCities() throws NoSuchDataException;
}
