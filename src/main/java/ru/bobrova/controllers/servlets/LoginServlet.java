package ru.bobrova.controllers.servlets;

import ru.bobrova.common.exceptions.UserDAOException;
import ru.bobrova.models.pojo.Users;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.bobrova.services.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);

    private IUserService userService;
    @Autowired(required = true)
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext
                (this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          req.getRequestDispatcher("/login.jsp").forward(req,resp);
          logger.trace("loginGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        //SessionListener sl = new SessionListener();

        String password = req.getParameter("password");
        try {
            Users user = userService.authorise(login,password);
            if (user.getId_user()!=0) {
                req.getSession().setAttribute("id",user.getId_user());
                req.getSession().setAttribute("name",user.getFirstname() + " " + user.getLastname());
                logger.trace("auth " + user.getLogin());
                resp.sendRedirect("/library/list");
            } else {
                logger.trace("no_auth");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/library/error.jsp");
        }
    }
}
