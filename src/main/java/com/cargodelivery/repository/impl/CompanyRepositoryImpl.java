package com.cargodelivery.repository.impl;

import com.cargodelivery.model.Company;
import com.cargodelivery.repository.CompanyRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRepositoryImpl implements CompanyRepository {
    /**
     * Logger log4j
     */
    private final static Logger logger = Logger.getLogger(UserRepositoryImpl.class);
    /**
     * Connection of database
     */
    private final Connection connection;

    /**
     * Init database connection
     *
     * @param connection of database
     */
    public CompanyRepositoryImpl(Connection connection) {
        this.connection = connection;
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
        try (PreparedStatement statement = connection.prepareStatement(SQLCompany.FINDBYID.QUERY)) {
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
