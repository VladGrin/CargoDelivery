package com.cargodelivery.repository.impl;

import com.cargodelivery.model.Company;
import com.cargodelivery.repository.CompanyRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CompanyRepositoryImplTest {

    private CompanyRepository companyRepository;

    private Connection connection;

    @Before
    public void before() {
        try {
            String userName = "root";
            String password = "1111";
            String connectionUrl = "jdbc:mysql://localhost:3306/cargodelivery?useSSL=true&characterEncoding=utf8";
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            companyRepository = new CompanyRepositoryImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see com.cargodelivery.repository.impl.CompanyRepositoryImpl#findCompanyById(int)
     */
    @Test
    public void getCompanyById() {
        Company companyById = companyRepository.findCompanyById(1);
        Company expectedCompany = new Company(1, "OOO \"Cargo Delivery\"", "г.Винница, ул.Короленко 15/113",
                "33944031", "339440326570", "ПАТ \"ОТП Банк\"", "300528", "26006455017522");
        assertEquals(expectedCompany, companyById);
    }
}