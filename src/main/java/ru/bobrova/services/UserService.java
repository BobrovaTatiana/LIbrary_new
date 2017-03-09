package ru.bobrova.services;

import ru.bobrova.common.exceptions.UserDAOException;
import ru.bobrova.models.dao.UserDAO;
import ru.bobrova.models.pojo.Users;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Tanusha on 27/02/2017.
 */
@Service
public class UserService implements IUserService {
    private UserDAO userDAO;

    //@Autowired
    //public UserService(UserDAO userDAO) {
       // this.userDAO = userDAO;
    //}

    @Override
    public Users authorise(String login, String password) throws UserDAOException {
        return userDAO.getUserByLoginAndPassword(login, password);
    }

    public static boolean registration(String firstname, String lastname, Date date_reg,
                                       String login, String password, String email) throws UserDAOException {
        return UserDAO.registrationUser(firstname, lastname, date_reg, login, password, email);
    }
}
