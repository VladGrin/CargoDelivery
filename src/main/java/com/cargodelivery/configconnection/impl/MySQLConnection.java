package com.cargodelivery.configconnection.impl;

import com.cargodelivery.configconnection.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConnection implements DBConnection {

    private final static Logger logger = Logger.getLogger(MySQLConnection.class);

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String userName = resource.getString("db.user");
            String password = resource.getString("db.password");
            String connectionUrl = resource.getString("db.url");
            Class.forName(resource.getString("db.driver"));
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            logger.info("The connection was successful");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("The connection was not successful. " + e);
        }
        return connection;
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            logger.info("Connection was closed successfully.");
            connection.close();
        } catch (SQLException e) {
            logger.info("Connection was not closed successfully. " + e);
        }
    }
}
