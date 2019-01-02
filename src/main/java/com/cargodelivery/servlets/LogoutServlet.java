package com.cargodelivery.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout.
 * Delete session.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();

        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("role");

        servletResponse.sendRedirect("/");
    }

}
