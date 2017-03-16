package ru.bobrova.models.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bobrova.models.connector.AcademConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Tanusha on 27/02/2017.
 */
@Component
public class HistoryDAO {
    private static Logger logger = Logger.getLogger(HistoryDAO.class);

    private static String SQL_CREATE_HISTORY = "INSERT INTO public.\"History\"(\n" +
            "\tid_user, id_book, date)\n" +
            "\tVALUES (?, ?, ?);";

    @Autowired
    public static boolean insertComment(int id_book, int id_user){
        java.util.Date date = new java.util.Date();
        try(Connection connection = AcademConnector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_HISTORY)) {
            preparedStatement.setInt(2, id_book);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted history" + count);
                connection.close();
                return true;
            }else{
                logger.debug("History for user " + id_user + " not insert");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }
}
