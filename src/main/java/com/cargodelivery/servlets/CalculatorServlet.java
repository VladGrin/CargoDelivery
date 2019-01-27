package com.cargodelivery.servlets;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.City;
import com.cargodelivery.model.User;
import com.cargodelivery.service.CalculateServise;
import com.cargodelivery.service.CityService;
import com.cargodelivery.service.DistanceService;
import com.cargodelivery.service.impl.CalculatorServiceImpl;
import com.cargodelivery.service.impl.CityServiceImpl;
import com.cargodelivery.service.impl.DistanceServiceImpl;
import com.cargodelivery.util.calculate.PriceCalculatorByCargoType;
import com.cargodelivery.util.calculate.PriceCalculatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(CalculatorServlet.class);
    private DistanceService distanceService = new DistanceServiceImpl();
    private CityService cityService = new CityServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        String cityTo = request.getParameter("cityTo");
        String cityFrom = request.getParameter("cityFrom");
        String cargoType = request.getParameter("cargoType");
        String weight = request.getParameter("weight");
        logger.info("Entered data: cityTo: " + cityTo + " cityFrom: " + cityFrom + " cargoType: " + cargoType + " weight: " + weight);

        int distance = distanceService.getDistanceBetweenTwoCities(cityTo, cityFrom);

        String orderPrice = null;
        String errorPrice = null;
        try {
            CalculateServise calculateServise = new CalculatorServiceImpl(new PriceCalculatorByCargoType(new PriceCalculatorFactory()));
            orderPrice = String.valueOf(calculateServise.getOrderPrice(cargoType, weight, distance));
            request.setAttribute("price", "Стоимость перевозки: " + orderPrice + " грн.");
            logger.info("Get price: " + orderPrice);
        } catch (IncorrectInputException e) {
            errorPrice = "Введены некоректные данные";
            request.setAttribute("priceError", errorPrice);
            logger.error("Exception" + e);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Set<City> cities = null;
        try {
            cities = cityService.getAllCities();
            logger.info("Servlet cities: " + cities);
        } catch (NoSuchDataException e) {
            logger.error("No any cities in servlet. " + e);
        }

        request.setAttribute("cities", cities);

        setRoleToRequest(request);

        request.getRequestDispatcher("/WEB-INF/view/calculator.jsp").forward(request, response);
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
}
