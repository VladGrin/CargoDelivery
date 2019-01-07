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

@WebServlet(name = "BillServlet")
public class BillServlet extends HttpServlet {

    private final String bill = "/WEB-INF/view/bill.jsp";
    private final DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(BillServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        Order order = getOrderByOrderId(orderId);
        request.setAttribute("order", order);

        User user = getUserByUserId(order.getUserId());
        request.setAttribute("user", user);

        Company company = getCompanyById(1);
        request.setAttribute("company", company);

        request.getRequestDispatcher(bill).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(bill).forward(request, response);
    }

    private Order getOrderByOrderId(String orderId) {
        Connection connection = dbConnection.getConnection();
        OrderService orderService = new OrderServiceImpl(connection);
        Order orderById = null;
        try {
            orderById = orderService.getOrderById(orderId);
            logger.info("Order " + orderId + " was finded");
        } catch (IncorrectInputException e) {
            logger.error("Order " + orderId + " was not finded: " + e);
        }
        dbConnection.closeConnection(connection);

        return orderById;
    }

    private User getUserByUserId(Integer userId) {
        Connection connection = dbConnection.getConnection();
        UserService userService = new UserServiceImpl(connection);
        User userById = null;
        try {
            userById = userService.getUserById(userId);
            logger.info("User " + userId + " was found");
        } catch (NoSuchDataException e) {
            logger.error("User " + userId + " was not found: " + e);
        }
        dbConnection.closeConnection(connection);

        return userById;
    }

    private Company getCompanyById(int id) {

        Connection connection = dbConnection.getConnection();
        CompanyService companyService = new CompanyServiceImpl(connection);
        Company company = companyService.getCompanyById(id);
        logger.info("Company " + id + " was found");
        dbConnection.closeConnection(connection);

        return company;
    }
}
