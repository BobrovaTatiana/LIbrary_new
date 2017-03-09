package ru.bobrova.models.dao;

import ru.bobrova.common.exceptions.UserDAOException;
import marshaling.Lib_users;
import ru.bobrova.models.connector.AcademConnector;
import ru.bobrova.models.pojo.Users;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.ParseException;
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

    public Lib_users loadUsers (Connection con) throws SQLException, ClassNotFoundException {
        Lib_users users = new Lib_users();
        users.setUsers("List of users");
        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".\"Users\"");
                while (rs.next()) {
                    Integer id = Integer.parseInt(rs.getString("id_user"));
                    String firstname = rs.getString("firstname").trim();
                    Date date = rs.getDate("date_reg");
                    String lastname = rs.getString("lastname").trim();
                    String email = rs.getString("email").trim();
                    String login1 = rs.getString("login").trim();

                    users.getUser().add(createUser(id, firstname, date, lastname,
                            email, login1));
                }
                rs.close();
                stmt.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //con.close();
        return users;
    }

    public Users createUser(int id, String firstname, Date date, String lastname,
                            String email, String login) throws ParseException {
        Users user = new Users();
        user.setId_user(id);
        user.setFirstname(firstname);
        user.setDate_reg(date);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setLogin(login);
        return user;
    }

    public void uploadUsers (Object object, Connection dbConnection) throws SQLException, ClassNotFoundException, InterruptedException {
        Lib_users users = (Lib_users) object;
        String insertTableSQL = "INSERT INTO public.\"Users\"(\n" +
                "\t\"id_user\", \"firstname\", \"lastname\", \"email\", \"date_reg\", \"login\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement prepareStatement = dbConnection.prepareStatement(insertTableSQL);
        dbConnection.setAutoCommit(false);

        for (int i = 0; i < users.getUser().size(); i++) {
            prepareStatement.setInt(1, users.getUser().get(i).getId_user());
            prepareStatement.setString(2, users.getUser().get(i).getFirstname());
            prepareStatement.setString(3, users.getUser().get(i).getLastname());
            prepareStatement.setString(4, users.getUser().get(i).getEmail());
            prepareStatement.setDate(5, new java.sql.Date(users.getUser().get(i).getDate_reg().getTime()));
            prepareStatement.setString(6, users.getUser().get(i).getLogin());
            prepareStatement.addBatch();
        }
        prepareStatement.executeBatch();
        dbConnection.commit();

        System.out.println("Record is inserted into Users table!");
        prepareStatement.close();
    }

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

}
