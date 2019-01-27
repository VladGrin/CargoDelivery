package com.cargodelivery.repository.impl;

import com.cargodelivery.configconnection.MySQLConfiguration;
import com.cargodelivery.repository.DistanceRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Volodymyr Hrinchenko
 */
public class DistanceRepositoryImpl implements DistanceRepository {
    /**
     * Logger log4j
     */
    private final static Logger logger = Logger.getLogger(DistanceRepositoryImpl.class);
    /**
     * Connection of database
     */
    private Connection connection;

    public DistanceRepositoryImpl() {
        this.connection = new MySQLConfiguration().getConnection();
    }

    public DistanceRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Find distance between two sities
     *
     * @param firstCity
     * @param secondCity
     * @return distance if it exists. If distance does not exist return 0.
     */
    @Override
    public int getDistanceBetweenTwoCities(String firstCity, String secondCity) {
        int distance = 0;

        try (PreparedStatement statement = connection.prepareStatement(SQLDistance.FINDDISTANCE.QUERY)) {
            statement.setString(1, firstCity);
            statement.setString(2, secondCity);
            statement.setString(3, secondCity);
            statement.setString(4, firstCity);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                distance = resultSet.getInt("distance");
            }
        } catch (SQLException e) {
            logger.error("Invalid connection.");
            e.printStackTrace();
        }
        logger.info("Distance was found from database : " + distance);
        return distance;
    }

    /**
     * Find delivery term between two sities
     *
     * @param firstCity
     * @param secondCity
     * @return delivery term if it exists. If delivery term does not exist return 0.
     */
    @Override
    public int getDeliveryTermBetweenTwoCities(String firstCity, String secondCity) {
        int deliveryTerm = 0;

        try (PreparedStatement statement = connection.prepareStatement(SQLDistance.FINDDELIVERYTERM.QUERY)) {
            statement.setString(1, firstCity);
            statement.setString(2, secondCity);
            statement.setString(3, secondCity);
            statement.setString(4, firstCity);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                deliveryTerm = resultSet.getInt("delivery_term");
            }
        } catch (SQLException e) {
            logger.error("Invalid connection.");
            e.printStackTrace();
        }
        logger.info("Delibery term was found from database : " + deliveryTerm);
        return deliveryTerm;
    }

    enum SQLDistance {
        FINDDISTANCE("SELECT DISTINCT d.distance FROM distance AS d\n" +
                "WHERE (d.firstCity = (?) AND d.secondCity = (?)) OR (d.firstCity = (?) AND d.secondCity = (?));"),
        FINDDELIVERYTERM("SELECT DISTINCT d.delivery_term FROM distance AS d\n" +
                "WHERE (d.firstCity = (?) AND d.secondCity = (?)) OR (d.firstCity = (?) AND d.secondCity = (?));");

        String QUERY;

        SQLDistance(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
