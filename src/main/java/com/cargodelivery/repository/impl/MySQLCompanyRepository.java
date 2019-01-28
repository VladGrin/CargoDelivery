package com.cargodelivery.repository.impl;

//import com.cargodelivery.configconnection.MySQLConfiguration;

import com.cargodelivery.configconnection.MySQLConfiguration;
import com.cargodelivery.model.Company;
import com.cargodelivery.repository.CompanyRepository;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLCompanyRepository implements CompanyRepository {
    /**
     * Logger log4j
     */
    private final static Logger logger = Logger.getLogger(MySQLCompanyRepository.class);

    private DataSource dataSource;

    public MySQLCompanyRepository() {
        this.dataSource = MySQLConfiguration.getInstance().getDataSource();
    }

    /**
     * Find Company by id
     *
     * @param id for find
     * @return entity Company if it exist.
     */
    @Override
    public Company findCompanyById(int id) {
        Company company = new Company();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLCompany.FINDBYID.QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                company.setId(id);
                company.setName(resultSet.getString("name"));
                company.setAddress(resultSet.getString("address"));
                company.setCodEDRPOU(resultSet.getString("codEDRPOU"));
                company.setCodINN(resultSet.getString("codINN"));
                company.setBank(resultSet.getString("bank"));
                company.setMfo(resultSet.getString("mfo"));
                company.setAccount(resultSet.getString("account"));
            }
        } catch (SQLException e) {
            logger.error("Invalid connection. ");
            e.printStackTrace();
        }
        logger.info("Company was found from database : " + company);
        return company;
    }

    enum SQLCompany {

        FINDBYID("SELECT * FROM company WHERE id = (?);");

        String QUERY;

        SQLCompany(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
