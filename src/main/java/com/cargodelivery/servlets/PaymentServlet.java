package com.cargodelivery.servlets;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.service.OrderService;
import com.cargodelivery.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/room/bill/payment")
public class PaymentServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(PaymentServlet.class);
    private OrderService orderService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        try {
            orderService.updatePaymentByOrderId(orderId, true);
        } catch (IncorrectInputException e) {
            logger.error("Incorrect id: " + e);
        }

        response.sendRedirect("/room");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }
    }
}
