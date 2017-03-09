package ru.bobrova.controllers;

import ru.bobrova.common.exceptions.UserDAOException;
import org.apache.log4j.Logger;
import ru.bobrova.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Date date_reg = new Date();

        try {
            if(UserService.registration(firstname, lastname, date_reg, login, password, email)){
                logger.trace("true");
                resp.sendRedirect("/library/login");
            }else{
                logger.trace("false");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/library/error.jsp");
        }
    }
}
