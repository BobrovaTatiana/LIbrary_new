package ru.bobrova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bobrova.common.exceptions.UserDAOException;
import ru.bobrova.models.dao.UserDAO;
import ru.bobrova.models.pojo.Users;

import java.util.Date;

/**
 * Created by Tanusha on 27/02/2017.
 */
@Service
public class UserService implements IUserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Users authorise(String login, String password) throws UserDAOException {
        return userDAO.getUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String firstname, String lastname, Date date_reg,
                                       String login, String password, String email) throws UserDAOException {
        return userDAO.registrationUser(firstname, lastname, date_reg, login, password, email);
    }

    @Override
    public boolean changePsw(String new_psw, int id_user) throws UserDAOException {
        return userDAO.changePsw(new_psw, id_user);
    }
}
