package com.cargodelivery.configconnection;

import com.cargodelivery.exception.ConnectionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private final static Logger logger = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool(){
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/database");
            connection = dataSource.getConnection();
            logger.info("The connection was successful");
        } catch (NamingException | SQLException e) {
            logger.error("The connection was not successful. " + e);
            throw new ConnectionException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
            logger.info("The connection was closed successful. ");
        } catch (SQLException e) {
            logger.error("The connection was not closed successfully. " + e);
        }
    }
}