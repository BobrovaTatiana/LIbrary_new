package ru.bobrova.controllers;

import ru.bobrova.models.dao.BookDAO;
import ru.bobrova.models.pojo.Books;
import org.apache.log4j.Logger;
import ru.bobrova.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by bot on 23.02.17.
 */
public class EditServlet extends HttpServlet {
    private static int st_id;
    private static Logger logger = Logger.getLogger(EditServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        st_id = id;
        logger.trace("id Book is " + id);
        Books book = BookDAO.getBookByID(id);
        req.setAttribute("book", book);
        req.setAttribute("username", req.getSession().getAttribute("name"));
        //logger.trace(req.getAttribute("username1"));
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String strId = req.getParameter("id");
        //int id = Integer.parseInt(strId);
        Books book = new Books();
        book.setId_book(st_id);
        book.setName(req.getParameter("name"));
        book.setDate_pub(Date.valueOf(req.getParameter("date_pub")));
        book.setGenre(req.getParameter("genre"));
        book.setAuthor(req.getParameter("author"));    //(Integer.parseInt(req.getParameter("group")));
        book.setYear(Integer.parseInt(req.getParameter("year")));
        book.setType_publication(req.getParameter("type"));

        boolean count = false;
        count = BookService.updateBook(book);
        if (count == true) {
            logger.trace("true");
            resp.sendRedirect("/library/list");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);

        }
    }
}
