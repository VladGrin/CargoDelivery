package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.model.User;
import com.cargodelivery.repository.impl.UserRepositoryImpl;
import com.cargodelivery.service.UserService;
import com.cargodelivery.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class RegistrationServlet extends HttpServlet {

    private final String registration = "/WEB-INF/view/registration.jsp";
    private final String room = "/WEB-INF/view/room.jsp";

    private final DBConnection dbConnection = new MySQLConnection();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(registration).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = dbConnection.getConnection();

        request.setCharacterEncoding("UTF8");

//        if (!requestIsValid(req)) {
//            doGet(req, resp);
//        }

        User user = new User.Builder().setName(request.getParameter("name"))
                .setSurname(request.getParameter("surname"))
                .setCity(request.getParameter("city"))
                .setPhone(request.getParameter("phone"))
                .setMail(request.getParameter("mail"))
                .setPassword(request.getParameter("password")).build();

        UserService userService = new UserServiceImpl(new UserRepositoryImpl(connection));
        userService.saveUser(user);

        dbConnection.closeConnection(connection);
        request.getRequestDispatcher(room).forward(request, response);
    }
}
