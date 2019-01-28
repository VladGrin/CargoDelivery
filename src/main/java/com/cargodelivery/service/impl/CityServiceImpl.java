package com.cargodelivery.service.impl;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import com.cargodelivery.repository.impl.MySQLCityRepository;
import com.cargodelivery.service.CityService;

import java.util.Set;

public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl() {
        this.cityRepository = new MySQLCityRepository();
    }

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Integer id) throws NoSuchDataException {
        City cityById = cityRepository.findById(id);
        if (cityById == null) {
            throw new NoSuchDataException("There are not city with id: " + id);
        }
        return cityById;
    }

    @Override
    public City getCityByName(String name) throws NoSuchDataException {
        City cityByName = cityRepository.findByName(name);
        if (cityByName == null) {
            throw new NoSuchDataException("There are not city with name: " + name);
        }
        return cityByName;
    }

    @Override
    public Set<City> getAllCities() throws NoSuchDataException {
        Set<City> allCities = cityRepository.findAll();
        if (allCities == null) {
            throw new NoSuchDataException("Cities list is empty.");
        }
        return allCities;
    }
}
