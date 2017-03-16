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
 * Created by Tanusha on 14/03/2017.
 */
@Controller
public class ProfileController {
    private static Logger logger = Logger.getLogger(ProfileController.class);
    private String msg = "";

    private IUserService userService;

    @Autowired(required = true)
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showPage(Model model, HttpSession session) {
        logger.trace("profileGet");
        model.addAttribute("username", session.getAttribute("name"));
        model.addAttribute("msg", msg);
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String post(Model model, HttpSession session,
                       @RequestParam(name = "psw") String psw_old,
                       @RequestParam(name = "psw1") String psw) {
        int id_user = (int) session.getAttribute("id");
        String user_psw = (String) session.getAttribute("psw");
        msg="";

        if (user_psw.trim().equals(psw_old.trim())){
            try {
                if(userService.changePsw(psw, id_user)) {
                    logger.trace("Пароль изменен");
                    return "redirect:" + "profile";
                } else {
                    logger.trace("false");
                    return "error";
                }
            } catch (Exception e) {
                logger.error(e);
                return "error";
            }
        }
        else {
            msg = "Пароль не изменен. Введен неверный пароль.";
            logger.trace(msg);
            //model.addAttribute("msg", "Пароль не изменен");
            return "redirect:" + "profile";
        }
    }
}
