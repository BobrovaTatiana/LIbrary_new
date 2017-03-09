package ru.bobrova.controllers.listeners;

import ru.bobrova.common.exceptions.UserDAOException;
import ru.bobrova.common.utils.Mailer;
import ru.bobrova.models.dao.UserDAO;
import ru.bobrova.models.pojo.Users;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//начало работы сессии
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private static Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        logger.trace(se.getSession().getAttribute("name"));
        logger.trace(se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        logger.trace(se.getSession().getAttribute("name"));
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
//        logger.trace(event.getValue());
        if ("id".equals(event.getName())&&event.getValue()!=null){
            Users user = null;
            try {
                user = UserDAO.getUserById((Integer) event.getValue());
            } catch (UserDAOException e) {
                logger.error(e);
            }
            //logger.trace("роль " + user.getRole().trim());
            if (user.getRole().trim().equals("admin")) {
                logger.trace("mail is send");
                Mailer.sendMail(user.getEmail(),"admin is logged in","admin is logged in");}
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
