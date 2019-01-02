package com.cargodelivery.servlets.filters;

import com.cargodelivery.configconnection.DBConnection;
import com.cargodelivery.configconnection.impl.MySQLConnection;
import com.cargodelivery.model.User;
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

        User.Role role = null;
        boolean existsUser = false;
        if (login != null && password != null) {
            Connection connection = dbConnection.getConnection();
            UserService userService = new UserServiceImpl(new UserRepositoryImpl(connection));
            existsUser = userService.existsUser(login, password);
            if (existsUser) {
                role = userService.getUserByLogin(login).getRole();
            }
            dbConnection.closeConnection(connection);
        }

        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
            role = (User.Role) session.getAttribute("role");
            func(request, response, role);
        } else if (existsUser) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", role);
            func(request, response, role);
        } else {
            func(request, response, User.Role.UNKNOWN);
        }
    }

    private void func(HttpServletRequest request, HttpServletResponse response, User.Role role) throws ServletException, IOException {
        switch (role) {
            case USER:
                request.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(request, response);break;
            case UNKNOWN:
                request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);break;
        }
    }

    @Override
    public void destroy() {
    }
}
