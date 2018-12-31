package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.impl.CityRepositoryImpl;
import com.cargodelivery.service.CityService;
import com.cargodelivery.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(name = "CalculatorServlet")
public class CalculatorServlet extends HttpServlet {

    private final String calculator = "/WEB-INF/view/calculator.jsp";
    private final DBConnection dbConnection = new MySQLConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        System.out.println(request.getParameter("cityFrom"));
        System.out.println(request.getParameter("cityTo"));
        System.out.println(request.getParameter("cargo"));
        System.out.println(request.getParameter("weight"));

        setCitiesFromDBToRequest(request, response);
        request.getRequestDispatcher(calculator).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCitiesFromDBToRequest(request, response);
    }

    private void setCitiesFromDBToRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = dbConnection.getConnection();

        CityService cityService = new CityServiceImpl(new CityRepositoryImpl(connection));
        Set<City> cities = cityService.getAllCities();
        request.setAttribute("cities", cities);

        dbConnection.closeConnection(connection);

        request.getRequestDispatcher(calculator).forward(request, response);
    }
}
