package com.cargodelivery.service.impl;

import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import com.cargodelivery.service.CityService;

import java.util.Set;

public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityBuIdById(Integer id) {
        return cityRepository.findById(id);
    }

    @Override
    public City getCityByNameByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public Set<City> getAllCities() {
        return cityRepository.findAll();
    }
}
