package com.cargodelivery.servlets;

import com.cargodelivery.exception.IncorrectInputException;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(RoomServlet.class);
    private OrderService orderService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        try {
            orderService.deleteOrderById(orderId);
            logger.info("Order " + orderId + " was deleted");
        } catch (IncorrectInputException e) {
            logger.error("Order " + orderId + " was not delete: " + e);
        }

        response.sendRedirect("/room");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }

        List<Order> orders = null;
        try {
            orders = orderService.getAllOrdersByUserId((Integer) userId).stream()
                    .sorted()
                    .collect(Collectors.toList());
            logger.info("Get orders list: " + orders);
        } catch (NoSuchDataException e) {
            logger.error("Order list is empty" + e);
        }
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(request, response);
    }
}
