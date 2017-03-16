package ru.bobrova.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bobrova.models.pojo.Books;
import ru.bobrova.services.IBookService;
import ru.bobrova.services.IHistoryService;

import javax.servlet.http.HttpSession;

/**
 * Created by Tanusha on 13/03/2017.
 */
@Controller
public class TextController {
    private static Logger logger = Logger.getLogger(TextController.class);
    private static int st_id;

    private IBookService bookService;
    private IHistoryService historyService;

    @Autowired(required = true)
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @Autowired(required = true)
    public void setHistoryService(IHistoryService historyService) {
        this.historyService = historyService;
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String showPage(Model model, HttpSession session,
                           @RequestParam(name = "id") int id) {
        logger.trace("id Book is " + id);
        st_id = id;
        Books book = bookService.getBookByID(id);
        String text = bookService.getTextByID(id);

        model.addAttribute("text", text);
        model.addAttribute("book", book);
        model.addAttribute("username", session.getAttribute("name"));
        logger.trace("get text of book");

        int id_user = (int) session.getAttribute("id");
        try {
            if(historyService.insertComment(st_id, id_user)){
                logger.trace("true");
            } else {
                logger.trace("false");
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return "text";
    }

    @RequestMapping(value = "/text", method = RequestMethod.POST)
    public String post(Model model, HttpSession session,
                       @RequestParam(name = "comment") String comment,
                       @RequestParam(name = "rating") String ratStr) {

        int id_user = (int) session.getAttribute("id");
        int rating = Integer.parseInt(ratStr);

        try {
            if(bookService.insertComment(st_id, id_user, comment, rating)){
                logger.trace("true");
                return "redirect:" + "/text?id=" + st_id;
            } else {
                logger.trace("false");
                return "error";
            }
        } catch (Exception e) {
            logger.error(e);
            return "error";
        }

    }
}
