package com.cargodelivery.configconnection;

import java.sql.Connection;

public interface DBConnection {
    Connection getConnection();
    void closeConnection(Connection connection);
}
