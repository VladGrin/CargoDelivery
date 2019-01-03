package com.cargodelivery.repository.impl;

import com.cargodelivery.repository.DistanceRepository;
import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DistanceRepositoryImplTest {

    @NotNull
    private DistanceRepository distanceRepository;

    @NotNull
    private Connection connection;

    @Before
    public void before() {
        try {
            String userName = "root";
            String password = "1111";
            String connectionUrl = "jdbc:mysql://localhost:3306/cargodelivery?useSSL=true&characterEncoding=utf8";
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            distanceRepository = new DistanceRepositoryImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
