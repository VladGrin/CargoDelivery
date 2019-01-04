package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.NoSuchDataException;
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
import java.util.List;

@WebServlet(name = "RoomServlet")
public class RoomServlet extends HttpServlet {

    private final String room = "/WEB-INF/view/room.jsp";
    private final DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(RoomServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = dbConnection.getConnection();
        OrderService orderService = new OrderServiceImpl(connection);
        Object id = request.getSession().getAttribute("id");
        List<Order> orders = null;
        try {
            orders = orderService.getAllOrdersByUserId((Integer) id);
            logger.info("Get orders list: " + orders);
        } catch (NoSuchDataException e) {
            logger.error("Order list is empty" + e);
        }
        request.setAttribute("orders", orders);

        request.getRequestDispatcher(room).forward(request, response);
    }
}
