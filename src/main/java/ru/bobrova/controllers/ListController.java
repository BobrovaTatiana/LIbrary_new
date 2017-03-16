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

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Tanusha on 09/03/2017.
 */
@Controller
public class ListController {
    private static Logger logger = Logger.getLogger(ListController.class);

    private IBookService bookService;

    @Autowired(required = true)
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showPage(Model model, HttpSession session) {
        List<Books> listBook = bookService.getAllBooks();
        model.addAttribute("listBook", listBook);
        model.addAttribute("username", session.getAttribute("name"));
        logger.trace("get list of books");
        return "list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String post(Model model,
                       @RequestParam(name = "login", defaultValue = "login") String login,
                       @RequestParam(name = "password", defaultValue = "password") String psw) {

        return "list";
    }
}
