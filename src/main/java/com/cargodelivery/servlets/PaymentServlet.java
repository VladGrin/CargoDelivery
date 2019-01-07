package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.model.Order;
import com.cargodelivery.service.OrderService;
import com.cargodelivery.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "PaymentServlet")
public class PaymentServlet extends HttpServlet {

    private final String room = "/WEB-INF/view/room.jsp";
    private final DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(BillServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        Connection connection = dbConnection.getConnection();
        OrderService orderService = new OrderServiceImpl(connection);
        try {
            orderService.updatePaymentByOrderId(orderId, true);
        } catch (IncorrectInputException e) {
            logger.error("Incorrect id: " + e);
        }
        dbConnection.closeConnection(connection);

        response.sendRedirect("/room");
//        request.getRequestDispatcher(room).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
