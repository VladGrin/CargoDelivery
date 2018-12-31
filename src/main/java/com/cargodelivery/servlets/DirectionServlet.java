package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.impl.CityRepositoryImpl;
import com.cargodelivery.service.CityService;
import com.cargodelivery.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

/**
 *
 */

public class DirectionServlet extends HttpServlet {

    private final String direction = "/WEB-INF/view/direction.jsp";
    private final DBConnection dbConnection = new MySQLConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = dbConnection.getConnection();

        CityService cityService = new CityServiceImpl(new CityRepositoryImpl(connection));
        Set<City> cities = cityService.getAllCities();
        request.setAttribute("cities", cities);

        dbConnection.closeConnection(connection);

        request.getRequestDispatcher(direction).forward(request, response);
    }
}
