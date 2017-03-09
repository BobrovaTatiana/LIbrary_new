package ru.bobrova.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tanusha on 02/03/2017.
 */
public class Logout extends HttpServlet {

    private static Logger logger = Logger.getLogger(Logout.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("до выхода " + req.getSession().getAttribute("id"));
        req.getSession().invalidate();
        logger.trace("после выхода " + req.getSession().getAttribute("id"));
        resp.sendRedirect("/library/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
