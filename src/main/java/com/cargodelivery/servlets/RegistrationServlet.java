package com.cargodelivery.servlets;

import com.cargodelivery.exception.DataAlreadyExistsException;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.User;
import com.cargodelivery.service.UserService;
import com.cargodelivery.service.impl.UserServiceImpl;
import com.cargodelivery.util.PasswordEncryption;
import com.cargodelivery.util.impl.PasswordEncryptionImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(RegistrationServlet.class);
    private final PasswordEncryption passwordEncryption = new PasswordEncryptionImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
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
        password = passwordEncryption.getEncryptedPassword(password);
        User.Role role = User.Role.USER;

        try {
            userService.saveUser(name, surname, city, phone, login, password, role);
            User userByLogin = userService.getUserByLogin(login);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", role);
            request.getSession().setAttribute("userId", userByLogin.getId());

            request.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(request, response);

        } catch (IncorrectInputException e) {
            setAttributeToRequest(request, name, surname, city, phone, login);
            logger.error("Incorrect input: " + e);
            request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
        } catch (DataAlreadyExistsException e) {
            setAttributeToRequest(request, name, surname, city, phone, login);
            logger.error("Such user already exists: " + e);
            request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
        } catch (NoSuchDataException e) {
            e.printStackTrace();
        }
    }

    private void setAttributeToRequest(HttpServletRequest request, String name, String surname, String city, String phone, String login) {
        request.setAttribute("registrationError", "Введены некоректные данные");
        request.setAttribute("name", name);
        request.setAttribute("surname", surname);
        request.setAttribute("city", city);
        request.setAttribute("phone", phone);
        request.setAttribute("mail", login);
    }
}
