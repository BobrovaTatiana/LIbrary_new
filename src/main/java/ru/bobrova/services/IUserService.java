package ru.bobrova.services;

import ru.bobrova.common.exceptions.UserDAOException;
import ru.bobrova.models.pojo.Users;

/**
 * Created by Tanusha on 07/03/2017.
 */
public interface IUserService {
    public Users authorise(String login, String password) throws UserDAOException;
}
