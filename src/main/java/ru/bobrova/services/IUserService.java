package ru.bobrova.services;

import ru.bobrova.common.exceptions.UserDAOException;
import ru.bobrova.models.pojo.Users;

import java.util.Date;

/**
 * Created by Tanusha on 07/03/2017.
 */
public interface IUserService {
    public Users authorise(String login, String password) throws UserDAOException;

    public boolean registration(String firstname, String lastname, Date date_reg,
                                       String login, String password, String email) throws UserDAOException;

    public boolean changePsw(String new_psw, int id_user) throws UserDAOException;

    }
