package com.cargodelivery.repository;

import com.cargodelivery.model.City;

import java.util.Set;

public interface CityRepository {
    boolean save(City city);
    City findById(Integer id);
    City findByName(String name);
    Set<City> findAll();
}
