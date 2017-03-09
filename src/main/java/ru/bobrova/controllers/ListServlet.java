package ru.bobrova.controllers;

import ru.bobrova.models.pojo.Books;
import org.apache.log4j.Logger;
import ru.bobrova.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Books> listBook= BookService.getAllBooks();
        req.setAttribute("listBook", listBook);
        req.setAttribute("username", req.getSession().getAttribute("name"));
        //logger.trace(req.getSession().getAttribute("name"));
        req.getRequestDispatcher("/list.jsp").forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
