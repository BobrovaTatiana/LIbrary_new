
import ru.bobrova.models.connector.AcademConnector;
import ru.bobrova.models.dao.BookDAO;
import ru.bobrova.models.dao.HistoryDAO;
import ru.bobrova.models.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final String ADURL = "jdbc:postgresql://localhost:5433/el_library";
    private static final String LOGIN = "postgres";;
    private static final String PASSWORD = "12345";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Connection con = AcademConnector.getConnection();
        Object lock = new Object();

        BookDAO bookloader = new BookDAO();
        UserDAO userloader = new UserDAO();
        HistoryDAO historyloader = new HistoryDAO();
        Thread th1 = new Thread(new marshaling.marsh_book("books.xml", bookloader.loadBooks(con)));
        Thread th2 = new Thread(new marshaling.marsh_book("users.xml", userloader.loadUsers(con)));
        Thread th3 = new Thread(new marshaling.marsh_book("history.xml", historyloader.loadHistory(con)));

        th1.start();
        th2.start();
        th3.start();
        con.close();


//        marshaling.Lib_book books = new marshaling.Lib_book();
//        marshaling.Lib_users users = new marshaling.Lib_users();
//        marshaling.Lib_history history = new marshaling.Lib_history();
//        Thread th1 = new Thread(new marshaling.Unmarsh("books.xml", books, con));
//        Thread th2 = new Thread(new marshaling.Unmarsh("users.xml", users, con));
//        Thread th3 = new Thread(new marshaling.Unmarsh("history.xml", history, con));
//
//        th1.start();
//        th2.start();
//        th3.start();
//
//        th1.join();
//        th2.join();
//        th3.join();
//
//        con.close();
    }
}
