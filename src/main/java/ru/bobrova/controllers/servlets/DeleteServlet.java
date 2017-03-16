package ru.bobrova.controllers.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bot on 23.02.17.
 */
public class DeleteServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(DeleteServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect("/library/list");

        String idstr = req.getParameter("id");
        int id  = Integer.parseInt(idstr);

//        if(BookService.deleteBook(id)){
//            logger.trace("true");
//            resp.sendRedirect("/library/list");
//        }else{
//            logger.trace("false");
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
    }
}
