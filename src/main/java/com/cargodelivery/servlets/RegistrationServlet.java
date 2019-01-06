package com.cargodelivery.servlets;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.DataAlreadyExistsException;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.User;
import com.cargodelivery.service.UserService;
import com.cargodelivery.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class RegistrationServlet extends HttpServlet {

    private final String registration = "/WEB-INF/view/registration.jsp";
    private final String room = "/WEB-INF/view/room.jsp";
    private final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    private final DBConnection dbConnection = new MySQLConnection();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(registration).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String login = request.getParameter("mail");
        String password = request.getParameter("password");
        User.Role role = User.Role.USER;

        String registrationError = null;
        request.setAttribute("registrationError", registrationError);

        Connection connection = dbConnection.getConnection();
        UserService userService = new UserServiceImpl(connection);
        try {
            userService.saveUser(name, surname, city, phone, login, password, role);
            User userByLogin = userService.getUserByLogin(login);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", role);
            request.getSession().setAttribute("userId", userByLogin.getId());

            request.getRequestDispatcher(room).forward(request, response);

        } catch (IncorrectInputException e) {
            registrationError = "Введены некоректные данные";
            request.setAttribute("registrationError", registrationError);
            logger.error("Incorrect input: " + e);
            request.getRequestDispatcher(registration).forward(request, response);
        } catch (DataAlreadyExistsException e) {
            registrationError = "Такой пользователь уже существует.";
            request.setAttribute("registrationError", registrationError);
            logger.error("Such user already exists: " + e);
            request.getRequestDispatcher(registration).forward(request, response);
        } catch (NoSuchDataException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
}
