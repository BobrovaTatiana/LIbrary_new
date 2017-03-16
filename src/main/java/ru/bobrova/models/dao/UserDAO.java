package ru.bobrova.models.dao;

import ru.bobrova.models.connector.AcademConnector;
import ru.bobrova.models.pojo.Users;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bobrova.common.exceptions.UserDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Tanusha on 27/02/2017.
 */
@Component
public class UserDAO {
    private static final String SQL_FIND_USER = "SELECT a.id_user, firstname, lastname, email, date_reg, login, role, password\n" +
            "\tFROM public.\"Users\" a, public.\"Users_psw\" b \n" +
            "    WHERE login = ? and password = ? and a.id_user=b.id_user;";

    private static Logger logger = Logger.getLogger(UserDAO.class);

    private static final String SQL_CREATE_USER = "INSERT INTO public.\"Users\"(\n" +
            "\t\"firstname\", \"lastname\", \"email\", \"date_reg\", \"login\", \"role\")\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_CREATE_USER_PSW = "INSERT INTO public.\"Users_psw\"(\n" +
            "\tid_user, password)\n" +
            "\tVALUES ((SELECT id_user FROM public.\"Users\" WHERE lastname = ? and firstname = ?), ?); ";

    private static final String SQL_USER_ID = "SELECT * FROM \"public\".\"Users\"\n" +
            "WHERE id_user = ?";
    private static final String SQL_UPDATE_PSW = "UPDATE public.\"Users_psw\"\n" +
            "\tSET password=?\n" +
            "\tWHERE id_user=?;";

    @Autowired
    public static Users getUserByLoginAndPassword(String login, String password) throws UserDAOException {

        logger.debug(login + " " + password);
        Users user = null;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find");
                user = new Users(
                        resultSet.getInt("id_user"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("date_reg"),
                        resultSet.getString("email"),
                        resultSet.getString("login"),
                        resultSet.getString("role"),
                        resultSet.getString("password")
                );
            }else{
                logger.debug(login + " " + password + " not found");
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return user;
    }

    @Autowired
    public static boolean registrationUser(String firstname, String lastname, Date date_reg,
                                           String login, String password, String email) throws UserDAOException {
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, new java.sql.Date(date_reg.getTime()));
            preparedStatement.setString(5, login);
            preparedStatement.setString(6, "guest");

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                preparedStatement.close();
                connection.close();
                registrationUserPSW(firstname,lastname,password);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public static boolean registrationUserPSW(String firstname, String lastname, String password) throws UserDAOException {
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER_PSW)) {
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(1, lastname);
            preparedStatement.setString(3, password);

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(firstname + " " + lastname + " " + password + " not inserted");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
           logger.error(e);
        }
        return false;
    }

    public static Users getUserById(int id) throws UserDAOException {

        Users user = null;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find");
                user = new Users(
                        resultSet.getInt("id_user"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("date_reg"),
                        resultSet.getString("email"),
                        resultSet.getString("login"),
                        resultSet.getString("role"),
                        "qwerty"
                );
            }else{
                logger.debug(id + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return user;
    }

    public boolean changePsw(String new_psw, int id_user) throws UserDAOException {
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PSW)) {
            preparedStatement.setString(1, new_psw);
            preparedStatement.setInt(2, id_user);

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("updated " + count);
                preparedStatement.close();
                connection.close();
                return true;
            }else{
                logger.debug(id_user + " not updated");
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

//    public static boolean changePsw1(String new_psw, int id_user) throws UserDAOException {
//        try(Connection connection = AcademConnector.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PSW)) {
//            preparedStatement.setString(1, new_psw);
//            preparedStatement.setInt(2, id_user);
//
//            int count = preparedStatement.executeUpdate();
//            if(count > 0){
//                logger.debug("updated " + count);
//                preparedStatement.close();
//                connection.close();
//                return true;
//            }else{
//                logger.debug(id_user + " not updated");
//                preparedStatement.close();
//                connection.close();
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//        }
//        return false;
//    }
}
