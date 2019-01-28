package com.cargodelivery.service.impl;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import com.cargodelivery.repository.impl.MySQLCityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CityServiceImplTest {

    private CityServiceImpl cityService;

    private CityRepository cityRepository;

    @Before
    public void before() {
        cityRepository = Mockito.mock(MySQLCityRepository.class);
        cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    public void getCityByIdTest() throws NoSuchDataException {

        Integer cityId = 123;
        City city = new City();

        Mockito.when(cityRepository.findById(cityId)).thenReturn(city);

        City result = cityService.getCityById(cityId);

        assertEquals(city, result);
    }

    @Test(expected = NoSuchDataException.class)
    public void getNullCityByIdTest() throws NoSuchDataException {

        cityService.getCityById(321);
    }

    @Test
    public void getCityByNameTest() throws NoSuchDataException {

        String cityByName = "Vinnitsa";
        City city = new City();

        Mockito.when(cityRepository.findByName(cityByName)).thenReturn(city);

        City result = cityService.getCityByName(cityByName);

        assertEquals(city, result);
    }

    @Test(expected = NoSuchDataException.class)
    public void getNullCityByNameTest() throws NoSuchDataException {

        cityService.getCityByName("Empty");
    }

    @Test
    public void getAllCitiesTest() throws NoSuchDataException {

        City firstCity = new City();
        City secondCity = new City();
        City therdCity = new City();
        Set<City> cities = new HashSet<>();
        cities.add(firstCity);
        cities.add(secondCity);
        cities.add(therdCity);

        Mockito.when(cityRepository.findAll()).thenReturn(cities);

        Set<City> result = cityService.getAllCities();

        assertEquals(cities, result);
    }

    @Test(expected = NoSuchDataException.class)
    public void getNullCitiesTest() throws NoSuchDataException {
        Mockito.when(cityRepository.findAll()).thenReturn(null);
        cityService.getAllCities();
    }
}