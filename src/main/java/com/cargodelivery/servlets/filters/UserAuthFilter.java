package com.cargodelivery.servlets.filters;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.User;
import com.cargodelivery.service.UserService;
import com.cargodelivery.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class UserAuthFilter implements Filter {

    private DBConnection dbConnection = new MySQLConnection();
    private final static Logger logger = Logger.getLogger(UserAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        User.Role role = null;
        boolean existsUser = false;
        if (login != null && password != null) {
            Connection connection = dbConnection.getConnection();
            UserService userService = new UserServiceImpl(connection);
            try {
                existsUser = userService.existsUser(login, password);
                if (existsUser) {
                    role = userService.getUserByLogin(login).getRole();
                }
                logger.info("Authentication user login: " + login);
            } catch (IncorrectInputException e) {
                logger.error("Authentication filter incorrect input: " + e);
            } catch (NoSuchDataException e) {
                logger.error("Authentication filter no data: " + e);
            }
            dbConnection.closeConnection(connection);
        }

        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
            role = (User.Role) session.getAttribute("role");

            sendRequestByRole(request, response, role);

        } else if (existsUser) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", role);

            sendRequestByRole(request, response, role);

        } else {
            sendRequestByRole(request, response, User.Role.UNKNOWN);
        }
    }

    private void sendRequestByRole(HttpServletRequest request, HttpServletResponse response, User.Role role) throws ServletException, IOException {
        switch (role) {
            case USER:
                request.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(request, response);
                break;
            case UNKNOWN:
                request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public void destroy() {
    }
}
