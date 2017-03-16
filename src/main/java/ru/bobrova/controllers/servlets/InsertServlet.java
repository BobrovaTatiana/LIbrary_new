package ru.bobrova.controllers.servlets;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;

/**
 * Created by bot on 23.02.17.
 */
public class InsertServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(InsertServlet.class);
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("username", req.getSession().getAttribute("name"));
//        req.getRequestDispatcher("/insert.jsp").forward(req, resp);
//        //logger.trace(req.getSession().getAttribute("name"));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        logger.trace("on post");
//
//        Books book = new Books();
//        book.setName(req.getParameter("name"));
//        book.setDate_pub(Date.valueOf(req.getParameter("date_pub")));
//        book.setGenre(req.getParameter("genre"));
//        book.setAuthor(req.getParameter("author"));
//        book.setYear(Integer.parseInt(req.getParameter("year")));
//        book.setType_publication(req.getParameter("type"));
//
//            if(bookService.insertBook(book)){
//                logger.trace("true");
//                resp.sendRedirect("/library/list");
//            }else{
//                logger.trace("false");
//                req.getRequestDispatcher("/error.jsp").forward(req, resp);
//            }
//    }
}
