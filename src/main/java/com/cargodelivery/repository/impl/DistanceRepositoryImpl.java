package com.cargodelivery.repository.impl;

import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.repository.DistanceRepository;
import com.sun.istack.internal.NotNull;
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
    private final static Logger logger = Logger.getLogger(UserRepositoryImpl.class);
    /**
     * Connection of database
     */
    @NotNull
    private Connection connection;

    public DistanceRepositoryImpl() {
        this.connection = new MySQLConnection().getConnection();
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("Distance was found from database : " + distance);
        return distance;
    }

    enum SQLDistance {
        FINDDISTANCE("SELECT DISTINCT d.distance FROM distance AS d\n" +
                "WHERE (d.firstCity = (?) AND d.secondCity = (?)) OR (d.firstCity = (?) AND d.secondCity = (?));");

        String QUERY;

        SQLDistance(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
