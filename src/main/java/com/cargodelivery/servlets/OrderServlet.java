package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.DataFormatter;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.configconnection.impl.MySQLDateFormatter;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.repository.DistanceRepository;
import com.cargodelivery.repository.impl.DistanceRepositoryImpl;
import com.cargodelivery.service.CalculateServise;
import com.cargodelivery.service.CityService;
import com.cargodelivery.service.OrderService;
import com.cargodelivery.service.impl.CalculatorServiceImpl;
import com.cargodelivery.service.impl.CityServiceImpl;
import com.cargodelivery.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {

    private final String order = "/WEB-INF/view/order.jsp";
    private final String room = "/WEB-INF/view/room.jsp";
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
        String deliveryAdrress = request.getParameter("deliveryAdrress");

        String createDate = dataFormatter.getCurrentDate();
        String endDate = getEndDate(idCityFrom, idCityTo, startDate);
        int price = getPriceAndSetToRequest(request, idCityFrom, idCityTo, cargoType, weight);


        Set<City> cities = getCitiesFromDB();
        String cityFrom = cities.stream().filter(city -> city.getId().toString().equals(idCityFrom)).findFirst().get().getName();
        String cityTo = cities.stream().filter(city -> city.getId().toString().equals(idCityTo)).findFirst().get().getName();

        logger.info("Entered data: userId: " + userId + " createDate: " + createDate + " cityFrom: " + cityFrom +
                " cityTo: " + cityTo +" cargoType: " + cargoType + " weight: " + weight +
                " startDate: " + startDate + " endDate: " + endDate + " recipient: " + recipient +
                " recipientPhone: " + recipientPhone + " deliveryAdrress: " + deliveryAdrress + " price: " + price);

        Connection connection = dbConnection.getConnection();
        OrderService orderServlet = new OrderServiceImpl(connection);
        try {
            orderServlet.saveOrder(userId, createDate, cityFrom, cityTo, cargoType, weight, startDate,
                    endDate, recipient, recipientPhone, deliveryAdrress, price);
            request.getRequestDispatcher("/room").forward(request, response);
        } catch (IncorrectInputException e) {
            logger.error("Incorrect input: " + e);
            response.sendRedirect("/room/order");
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Set<City> cities = getCitiesFromDB();
        request.setAttribute("cities", cities);

        request.getRequestDispatcher(order).forward(request, response);
    }

    private String getEndDate(String cityFrom, String cityTo, String startDate) {
        Connection connection = dbConnection.getConnection();

        DistanceRepository distanceRepository = new DistanceRepositoryImpl(connection);
        int deliveryTerm = distanceRepository.getDeliveryTermBetweenTwoCities(cityFrom, cityTo);

        dbConnection.closeConnection(connection);

        return dataFormatter.getEndDate(startDate, deliveryTerm);
    }

    private int getPriceAndSetToRequest(HttpServletRequest request, String cityFrom, String cityTo, String cargoType, String weight) {
        Connection connection = dbConnection.getConnection();
        int orderPrice = 0;
        try {
            CalculateServise calculateServise = new CalculatorServiceImpl(connection);
            orderPrice = calculateServise.getOrderPrice(cityTo, cityFrom, cargoType, weight);
            logger.info("Get price: " + orderPrice);
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