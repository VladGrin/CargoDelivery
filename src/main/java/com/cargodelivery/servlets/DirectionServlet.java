package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.model.User;
import com.cargodelivery.service.CityService;
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

@WebServlet("/direction")
public class DirectionServlet extends HttpServlet {

    private final DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(DirectionServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = dbConnection.getConnection();

        CityService cityService = new CityServiceImpl(connection);
        Set<City> cities = null;
        try {
            cities = cityService.getAllCities();
        } catch (NoSuchDataException e) {
            logger.error("No any cities in DirectionServlet." + e);
        }
        logger.info("Cities list (direction): " + cities);
        dbConnection.closeConnection(connection);

        request.setAttribute("cities", cities);

        Object role = request.getSession().getAttribute("role");
        if (role == null) {
            request.setAttribute("role", 0);
        } else {
            User.Role userRole = (User.Role) role;
            request.setAttribute("role", userRole.ordinal());
        }

        request.getRequestDispatcher("/WEB-INF/view/direction.jsp").forward(request, response);
    }
}
