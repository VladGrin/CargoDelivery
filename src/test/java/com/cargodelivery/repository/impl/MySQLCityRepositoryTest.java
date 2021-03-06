package com.cargodelivery.repository.impl;

import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MySQLCityRepositoryTest {

    private CityRepository cityRepository;

    private Connection connection;

    @Before
    public void before() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        try {
            String userName = resource.getString("db.user");
            String password = resource.getString("db.password");
            String connectionUrl = resource.getString("db.url");
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            cityRepository = new MySQLCityRepository(connection);
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
     * @see MySQLCityRepository#findById(Integer)
     */
    @Test
    public void findCityByIdWhichExistThenReturnCity() {
        City city = cityRepository.findById(1);
        City expectedCity = new City(1, "Винница");
        assertEquals(city, expectedCity);
    }

    /**
     * @see MySQLCityRepository#findById(Integer)
     */
    @Test
    public void findCityByIdWhichIsNotExistThenReturnNull() {
        City city = cityRepository.findById(0);
        assertNull(city);
    }

    /**
     * @see MySQLCityRepository#findByName(String)
     */
    @Test
    public void findCityByNameWhichExistThenReturnCity() {
        City city = cityRepository.findByName("Винница");
        City expectedCity = new City(1, "Винница");
        assertEquals(city, expectedCity);
    }

    /**
     * @see MySQLCityRepository#findByName(String)
     */
    @Test
    public void findCityByNameWhichIsNotExistThenReturnNull() {
        City city = cityRepository.findByName("Фуфу");
        assertNull(city);
    }

    /**
     * @see MySQLCityRepository#findAll()
     */
    @Test
    public void findAllCitiesByNameWhichExistThenReturnSetCities() {
        Set<City> cities = cityRepository.findAll();
        cities = cities.stream().limit(2).collect(Collectors.toSet());
        City city1 = new City(1, "Винница");
        City city2 = new City(2, "Киев");
        Set<City> expectedCities = new TreeSet<>(Arrays.asList(city1, city2));
        assertEquals(cities, expectedCities);
    }
}