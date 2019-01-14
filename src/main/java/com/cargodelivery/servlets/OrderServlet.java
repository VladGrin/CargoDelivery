package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.service.CalculateServise;
import com.cargodelivery.service.CityService;
import com.cargodelivery.service.DistanceService;
import com.cargodelivery.service.OrderService;
import com.cargodelivery.service.impl.CalculatorServiceImpl;
import com.cargodelivery.service.impl.CityServiceImpl;
import com.cargodelivery.service.impl.DistanceServiceImpl;
import com.cargodelivery.service.impl.OrderServiceImpl;
import com.cargodelivery.util.DataFormatter;
import com.cargodelivery.util.calculate.PriceCalculatorByCargoType;
import com.cargodelivery.util.calculate.PriceCalculatorFactory;
import com.cargodelivery.util.impl.MySQLDateFormatter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet("/room/order")
public class OrderServlet extends HttpServlet {

    private final DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(CalculatorServlet.class);
    private final DataFormatter dataFormatter = new MySQLDateFormatter();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        int userId = (int) request.getSession().getAttribute("userId");
        String idCityFrom = request.getParameter("cityFrom");
        String idCityTo = request.getParameter("cityTo");
        String cargoType = request.getParameter("cargoType");
        String weight = request.getParameter("weight");
        String startDate = request.getParameter("startDate");
        String recipient = request.getParameter("recipient");
        String recipientPhone = request.getParameter("recipientPhone");
        String deliveryAddress = request.getParameter("deliveryAddress");

        String createDate = dataFormatter.getCurrentDate();
        String endDate = getEndDate(idCityFrom, idCityTo, startDate);
        int price = getPrice(idCityFrom, idCityTo, cargoType, weight);

        Set<City> cities = getCitiesFromDB();
        String cityFrom = cities.stream().filter(city -> city.getId().toString().equals(idCityFrom)).findFirst().get().getName();
        String cityTo = cities.stream().filter(city -> city.getId().toString().equals(idCityTo)).findFirst().get().getName();

        logger.info("Entered data: userId: " + userId + " createDate: " + createDate + " cityFrom: " + cityFrom +
                " cityTo: " + cityTo + " cargoType: " + cargoType + " weight: " + weight +
                " startDate: " + startDate + " endDate: " + endDate + " recipient: " + recipient +
                " recipientPhone: " + recipientPhone + " deliveryAddress: " + deliveryAddress + " price: " + price);

        Connection connection = dbConnection.getConnection();
        OrderService orderServlet = new OrderServiceImpl(connection);
        try {
            orderServlet.saveOrder(userId, createDate, cityFrom, cityTo, cargoType, weight, startDate,
                    endDate, recipient, recipientPhone, deliveryAddress, price);
            response.sendRedirect("/room");
        } catch (IncorrectInputException e) {
            logger.error("Incorrect input: " + e);
            request.setAttribute("registrationError", "Введены некоректные данные");
            request.setAttribute("startDate", startDate);
            request.setAttribute("weight", weight);
            request.setAttribute("recipient", recipient);
            request.setAttribute("recipientPhone", recipientPhone);
            request.setAttribute("deliveryAddress", deliveryAddress);
            request.setAttribute("cities", cities);
            request.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(request, response);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }

        Set<City> cities = getCitiesFromDB();
        request.setAttribute("cities", cities);

        request.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(request, response);
    }

    private String getEndDate(String cityFrom, String cityTo, String startDate) {
        Connection connection = dbConnection.getConnection();

        DistanceService distanceService = new DistanceServiceImpl(connection);
        int deliveryTerm = distanceService.getDeliveryTermBetweenTwoCities(cityFrom, cityTo);

        dbConnection.closeConnection(connection);

        return dataFormatter.getEndDate(startDate, deliveryTerm);
    }

    private int getPrice(String cityFrom, String cityTo, String cargoType, String weight) {
        Connection connection = dbConnection.getConnection();
        int orderPrice = 0;
        try {
            DistanceService distanceService = new DistanceServiceImpl(connection);
            int distance = distanceService.getDistanceBetweenTwoCities(cityFrom, cityTo);
            CalculateServise calculateServise = new CalculatorServiceImpl(new PriceCalculatorByCargoType(new PriceCalculatorFactory()));
            orderPrice = calculateServise.getOrderPrice(cargoType, weight, distance);
            logger.info("Distance: " + distance + "Get price: " + orderPrice);
        } catch (IncorrectInputException e) {
            logger.error("Incorrect input data: " + e);
        }
        dbConnection.closeConnection(connection);
        return orderPrice;
    }

    private Set<City> getCitiesFromDB() {
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
        return cities;
    }
}
