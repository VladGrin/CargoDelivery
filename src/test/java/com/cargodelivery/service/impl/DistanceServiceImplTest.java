package com.cargodelivery.service.impl;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import com.cargodelivery.repository.DistanceRepository;
import com.cargodelivery.repository.impl.MySQLCityRepository;
import com.cargodelivery.repository.impl.MySQLDistanceRepository;
import com.cargodelivery.service.DistanceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DistanceServiceImplTest {

    private DistanceServiceImpl distanceService;

    private DistanceRepository distanceRepository;

    @Before
    public void before() {
        distanceRepository = Mockito.mock(MySQLDistanceRepository.class);
        distanceService = new DistanceServiceImpl(distanceRepository);
    }

    @Test
    public void getDistanceBetweenTwoCitiesTest() {

        String firstCity = "Vinnitsa";
        String secondCity = "Kiev";

        Mockito.when(distanceRepository.getDistanceBetweenTwoCities(firstCity, secondCity)).thenReturn(300);

        int result = distanceService.getDistanceBetweenTwoCities(firstCity, secondCity);

        assertEquals(300, result);
    }

    @Test
    public void getDeliveryTermBetweenTwoCitiesTest() {
        String firstCity = "Vinnitsa";
        String secondCity = "Kiev";

        Mockito.when(distanceRepository.getDeliveryTermBetweenTwoCities(firstCity, secondCity)).thenReturn(2);

        int result = distanceService.getDeliveryTermBetweenTwoCities(firstCity, secondCity);

        assertEquals(2, result);
    }
}
