package com.cargodelivery.configconnection;

import com.cargodelivery.exception.ConnectionException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

public class MySQLConfiguration {

    private final static Logger logger = Logger.getLogger(MySQLConfiguration.class);

    private static ComboPooledDataSource dataSource;

    private static MySQLConfiguration mySQLConfiguration;

    private MySQLConfiguration() { }

    public static MySQLConfiguration getInstance(){

        if(mySQLConfiguration == null){
            mySQLConfiguration = new MySQLConfiguration();
        }

        return mySQLConfiguration;
    }

    public DataSource getDataSource() {

        if (dataSource == null) {
            dataSource = buildPool();
        }

        return dataSource;
    }

    private ComboPooledDataSource buildPool() {

        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String userName = resource.getString("db.user");
            String password = resource.getString("db.password");
            String connectionUrl = resource.getString("db.url");
            String driver = resource.getString("db.driver");

            int minPoolSize = Integer.parseInt(resource.getString("db.pool.c3p0.min-pool-size"));
            int maxPoolSize = Integer.parseInt(resource.getString("db.pool.c3p0.max-pool-size"));
            int maxStatements = Integer.parseInt(resource.getString("db.pool.c3p0.max-statements"));
            int acquireIncrement = Integer.parseInt(resource.getString("db.pool.c3p0.acquire-increment"));

            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setUser(userName);
            cpds.setPassword(password);
            cpds.setJdbcUrl(connectionUrl);
            cpds.setDriverClass(driver);

            cpds.setMinPoolSize(minPoolSize);
            cpds.setMaxPoolSize(maxPoolSize);
            cpds.setMaxStatements(maxStatements);
            cpds.setAcquireIncrement(acquireIncrement);

            logger.info("The pool was successfully built.");
            return cpds;
        } catch (PropertyVetoException e) {
            logger.error("The pool was not build. ", e);
            throw new ConnectionException(e);
        }
    }
}