package com.cargodelivery.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout.
 * Delete session.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();

        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("role");
        session.removeAttribute("id");
        session.removeAttribute("userId");

        logger.info("The user left his room.");

        servletResponse.sendRedirect("/");
    }

}
