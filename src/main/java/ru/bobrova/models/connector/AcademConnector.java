package ru.bobrova.models.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcademConnector {

    private static Logger logger = Logger.getLogger(AcademConnector.class);

    private static Connection connection;

    private static AcademConnector ourInstance = new AcademConnector();

    public static AcademConnector getInstance() {
        return ourInstance;
    }

    private AcademConnector() {
    }

    /**Возвращает подключение к БД
     * @return connection
     */
    public static Connection getConnection() {

        try {
            if (connection!=null) connection.close();
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/el_library";
            connection = DriverManager.getConnection(url, "postgres", "12345");

            logger.trace("Successful connect to base: "+url);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            System.out.println("Не найден драйвер");
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Ошибка подключение к БД!");
        }
        return connection;
    }

}
