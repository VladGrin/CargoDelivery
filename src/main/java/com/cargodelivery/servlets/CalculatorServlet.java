package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.model.User;
import com.cargodelivery.service.CalculateServise;
import com.cargodelivery.service.CityService;
import com.cargodelivery.service.impl.CalculatorServiceImpl;
import com.cargodelivery.service.impl.CityServiceImpl;
import org.apache.log4j.Logger;

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
    private final CalculateServise calculateServise = new CalculatorServiceImpl();
    private final static Logger logger = Logger.getLogger(CalculatorServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        String cityTo = request.getParameter("cityTo");
        String cityFrom = request.getParameter("cityFrom");
        String cargoType = request.getParameter("cargoType");
        String weight = request.getParameter("weight");
        logger.info("Entered data: cityTo: " + cityTo + " cityFrom: " + cityFrom + " cargoType: " + cargoType + " weight: " + weight);

        String orderPrice = null;
        try {
            orderPrice = String.valueOf(calculateServise.getOrderPrice(cityTo, cityFrom, cargoType, weight));
            request.setAttribute("price", orderPrice);
            logger.info("Get price: " + orderPrice);
        } catch (IncorrectInputException e) {
            request.setAttribute("priceError", "Введены некоректные данные.");
            logger.error("Exception" + e);
        }

        setRoleToRequest(request);

        setCitiesFromDBToRequest(request, response);

//        request.getRequestDispatcher(calculator).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        setRoleToRequest(request);

        setCitiesFromDBToRequest(request, response);
    }

    private void setRoleToRequest(HttpServletRequest request) {
        Object role = request.getSession().getAttribute("role");
        if (role == null) {
            request.setAttribute("role", 0);
        } else {
            User.Role userRole = (User.Role) role;
            request.setAttribute("role", userRole.ordinal());
        }
    }

    private void setCitiesFromDBToRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbConnection.getConnection();

        CityService cityService = new CityServiceImpl(connection);
        Set<City> cities = null;
        try {
            cities = cityService.getAllCities();
            logger.info("Servlet cities: " + cities);
        } catch (NoSuchDataException e) {
            logger.error("No any cities in servlet. " + e);
        }
        dbConnection.closeConnection(connection);

        request.setAttribute("cities", cities);

        request.getRequestDispatcher(calculator).forward(request, response);
    }
}
