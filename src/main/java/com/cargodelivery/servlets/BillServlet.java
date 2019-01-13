package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.Company;
import com.cargodelivery.model.Order;
import com.cargodelivery.model.User;
import com.cargodelivery.service.CompanyService;
import com.cargodelivery.service.OrderService;
import com.cargodelivery.service.UserService;
import com.cargodelivery.service.impl.CompanyServiceImpl;
import com.cargodelivery.service.impl.OrderServiceImpl;
import com.cargodelivery.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/room/bill")
public class BillServlet extends HttpServlet {

    private final DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(BillServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        Connection connection = dbConnection.getConnection();

        OrderService orderService = new OrderServiceImpl(connection);
        Order orderById = null;
        try {
            orderById = orderService.getOrderById(orderId);
            logger.info("Order " + orderId + " was finded");
        } catch (IncorrectInputException e) {
            logger.error("Order " + orderId + " was not finded: " + e);
        }
        request.setAttribute("order", orderById);

        UserService userService = new UserServiceImpl(connection);
        User userById = null;
        try {
            userById = userService.getUserById(orderById.getUserId());
            logger.info("User " + orderById.getUserId() + " was found");
        } catch (NoSuchDataException e) {
            logger.error("User " + orderById.getUserId() + " was not found: " + e);
        }
        request.setAttribute("user", userById);

        CompanyService companyService = new CompanyServiceImpl(connection);
        Company company = companyService.getCompanyById(1);
        logger.info("Company " + 1 + " was found");
        request.setAttribute("company", company);

        dbConnection.closeConnection(connection);

        request.getRequestDispatcher("/WEB-INF/view/bill.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/view/bill.jsp").forward(request, response);
    }
}
