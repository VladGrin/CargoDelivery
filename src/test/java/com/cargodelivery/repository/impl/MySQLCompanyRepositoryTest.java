//package com.cargodelivery.repository.impl;
//
//import com.cargodelivery.model.Company;
//import com.cargodelivery.repository.CompanyRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import static org.junit.Assert.*;
//
//public class MySQLCompanyRepositoryTest {
//
//    private CompanyRepository companyRepository;
//
//    private Connection connection;
//
//    @Before
//    public void before() {
//        ResourceBundle resource = ResourceBundle.getBundle("database");
//        try {
//            String userName = resource.getString("db.user");
//            String password = resource.getString("db.password");
//            String connectionUrl = resource.getString("db.url");
//            connection = DriverManager.getConnection(connectionUrl, userName, password);
//            companyRepository = new MySQLCompanyRepository();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @After
//    public void after() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @see MySQLCompanyRepository#findCompanyById(int)
//     */
//    @Test
//    public void getCompanyById() {
//        Company companyById = companyRepository.findCompanyById(1);
//        Company expectedCompany = new Company(1, "OOO \"Cargo Delivery\"", "г.Винница, ул.Короленко 15/113",
//                "33944031", "339440326570", "ПАТ \"ОТП Банк\"", "300528", "26006455017522");
//        assertEquals(expectedCompany, companyById);
//    }
//}