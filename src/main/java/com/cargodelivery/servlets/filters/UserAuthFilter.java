package com.cargodelivery.servlets.filters;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.repository.impl.UserRepositoryImpl;
import com.cargodelivery.service.UserService;
import com.cargodelivery.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class UserAuthFilter implements Filter {

    private DBConnection dbConnection = new MySQLConnection();

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

        HttpSession session = request.getSession();

        if (login != null && password != null) {

            Connection connection = dbConnection.getConnection();
            UserService userService = new UserServiceImpl(new UserRepositoryImpl(connection));
            boolean existsUser = userService.existsUser(login, password);
            dbConnection.closeConnection(connection);

            if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
                request.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(request, response);

            } else if (existsUser) {
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("password", password);
                request.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
