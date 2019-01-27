package com.cargodelivery.configconnection;

import com.cargodelivery.exception.ConnectionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConfiguration {

    private final static Logger logger = Logger.getLogger(MySQLConfiguration.class);
    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            connection = buildConnection();
        }
        return connection;
    }

    private Connection buildConnection() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String userName = resource.getString("db.user");
            String password = resource.getString("db.password");
            String connectionUrl = resource.getString("db.url");
            Class.forName(resource.getString("db.driver"));
            Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            logger.info("The connection was successful");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("The connection was not successful. " + e);
            throw new ConnectionException(e);
        }
    }
}
