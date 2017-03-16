package ru.bobrova.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bobrova.services.IUserService;

import javax.servlet.http.HttpSession;

/**
 * Created by Tanusha on 09/03/2017.
 */
@Controller
public class RegistrationController {
    private static Logger logger = Logger.getLogger(RegistrationController.class);

    private IUserService userService;

    @Autowired(required = true)
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showPage() {
        logger.trace("registration Get");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String post(Model model, HttpSession session,
                       @RequestParam(name = "firstname") String firstname,
                       @RequestParam(name = "lastname") String lastname,
                       @RequestParam(name = "login") String login,
                       @RequestParam(name = "password") String psw,
                       @RequestParam(name = "email") String email
                       //@RequestParam(name = "date_reg", defaultValue = "date_reg") String date_reg
    ) {
        java.util.Date date_reg = new java.util.Date();
        try {
            if(userService.registration(firstname, lastname, date_reg, login, psw, email) == true){
                logger.trace("true");
                return "redirect:" + "/login";
            } else {
                logger.trace("false");
                return "error";
            }
        } catch (Exception e) {
            logger.error(e);
            return "error";
        }
        //return "login";
    }
}
