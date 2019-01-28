package com.cargodelivery.service.impl;

import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class CityServiceImplTest {

    private CityServiceImpl cityService;

    private Connection connection;

    @Before
    public void before() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
//        try {
//            String userName = resource.getString("db.user");
//            String password = resource.getString("db.password");
//            String connectionUrl = resource.getString("db.url");
//            connection = DriverManager.getConnection(connectionUrl, userName, password);
//            cityRepository = new MySQLCityRepository();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        cityService = new CityServiceImpl();
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * @see CityServiceImpl#getCityById(Integer)
//     */
//    @Test
//    public void getCityByIdWhichExistThenReturnCity() throws NoSuchDataException {
//        City city = cityService.getCityById(1);
//        City expectedCity = new City(1, "Винница");
//        assertEquals(city, expectedCity);
//    }

    @Test
    public void getCityByName() {
    }

    @Test
    public void getAllCities() {
    }
}