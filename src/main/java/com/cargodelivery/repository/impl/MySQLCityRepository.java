package com.cargodelivery.repository.impl;

import com.cargodelivery.configconnection.MySQLConfiguration;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.CityRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Author : Volodymyr Hrinchenko
 */
public class MySQLCityRepository implements CityRepository {
    /**
     * Logger log4j
     */
    final static Logger logger = Logger.getLogger(MySQLCityRepository.class);
    /**
     * Connection of database
     */
    private Connection connection;
    /**
     * Create empty constructor
     * Init database connection by MySQL connection
     */
    public MySQLCityRepository() {
        this.connection = new MySQLConfiguration().getConnection();
    }
    /**
     * Init database connection
     * @param connection of database
     */
    public MySQLCityRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create/save city in database
     *
     * @param city for create
     * @return if creating success true.
     */
    @Override
    public boolean save(City city) {
        boolean isSave = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLCity.SAVE.QUERY)) {
            statement.setString(1, city.getName());
            isSave = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("City " + city.getName() + " was saved to database : " + isSave);
        return isSave;
    }

    /**
     * Find city by id
     *
     * @param id for find
     * @return entity City if it exist. If entity does not exist return null.
     */
    @Override
    public City findById(Integer id) {
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLCity.FINDBYID.QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City(id, resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("City was found from database : " + city);
        return city;
    }

    /**
     * Find city by name
     *
     * @param name for find
     * @return entity City if it exist. If entity does not exist return null.
     */
    @Override
    public City findByName(String name) {
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLCity.FINDBYNAME.QUERY)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City(resultSet.getInt("id"), name);
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("City was found from database : " + city);
        return city;
    }

    /**
     * Find all cities
     *
     * @return entities City if they exist. If entities does not exist return null.
     */
    @Override
    public Set<City> findAll() {
        Set<City> cities = new TreeSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLCity.FINDALL.QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                City city = new City(resultSet.getInt("id"), resultSet.getString("name"));
                cities.add(city);
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        cities = cities.isEmpty() ? null : cities;
        logger.info("Cities were found from database : " + cities);
        return cities;
    }

    enum SQLCity {
        SAVE("INSERT INTO cities (id, name) VALUES (DEFAULT, (?));"),
        FINDBYID("SELECT c.name FROM cities AS c WHERE c.id = (?);"),
        FINDBYNAME("SELECT c.id FROM cities AS c WHERE c.name = (?)"),
        FINDALL("SELECT * FROM cities");

        String QUERY;

        SQLCity(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
