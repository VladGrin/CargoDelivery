package com.cargodelivery.configconnection.impl;

import com.cargodelivery.configconnection.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DBConnection {

    private final static Logger logger = Logger.getLogger(MySQLConnection.class);

    @Override
    public Connection getConnection() {
        String userName = "root";
        String password = "1111";
        String connectionUrl = "jdbc:mysql://localhost:3306/cargodelivery?useSSL=true&characterEncoding=utf8";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
