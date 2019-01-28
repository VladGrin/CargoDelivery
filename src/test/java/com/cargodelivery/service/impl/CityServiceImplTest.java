package com.cargodelivery.service.impl;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import com.cargodelivery.repository.impl.MySQLCityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

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
}