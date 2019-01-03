package com.cargodelivery.repository.impl;

import com.cargodelivery.repository.DistanceRepository;
import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DistanceRepositoryImplTest {

    @NotNull
    private DistanceRepository distanceRepository;

    @Before
    public void before() {
        distanceRepository = new DistanceRepositoryImpl();
    }

    /**
     * @see com.cargodelivery.repository.impl.DistanceRepositoryImpl#getDistanceBetweenTwoCities(String, String)
     */
    @Test
    public void getDistanceBetweenTwoCities() {
        int expectedDistance = 270;
        String firstCity = "1";
        String secondCity = "2";
        int distanceBetweenTwoCities = distanceRepository.getDistanceBetweenTwoCities(firstCity, secondCity);
        assertEquals(expectedDistance, distanceBetweenTwoCities);
    }

    /**
     * @see com.cargodelivery.repository.impl.DistanceRepositoryImpl#getDistanceBetweenTwoCities(String, String)
     */
    @Test
    public void WhenCitiesSameThenReturnZero() {
        int expectedDistance = 0;
        String firstCity = "1";
        String secondCity = "1";
        int distanceBetweenTwoCities = distanceRepository.getDistanceBetweenTwoCities(firstCity, secondCity);
        assertEquals(expectedDistance, distanceBetweenTwoCities);
    }
}
