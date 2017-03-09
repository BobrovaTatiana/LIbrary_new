package marshaling;

/**
 * Created by Tanusha on 21/02/2017.
 */

import ru.bobrova.models.dao.BookDAO;
import ru.bobrova.models.dao.HistoryDAO;
import ru.bobrova.models.dao.UserDAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;

public class Unmarsh implements Runnable {
    private String path;
    private Object object;
    private Connection con;
    private Object lock = new Object();

    public Unmarsh(String path, Object object, Connection con) {
        this.path = path;
        this.object = object;
        this.con = con;
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(file);
            synchronized (con) {
            while (true) {
                try {
                    String s = object.getClass().getSimpleName();
                    if (s.equals("Lib_book")) {//System.out.println(1);
                        BookDAO bookupload = new BookDAO();
                        bookupload.uploadBooks(object, con);
                        Thread.sleep(2000);
                        con.notifyAll();
                        return;
                    } else if (s.equals("Lib_users")) {//System.out.println(2);
                        UserDAO userupload = new UserDAO();
                        userupload.uploadUsers(object, con);
                        Thread.sleep(2000);
                        con.notifyAll();
                        return;
                    } else if (s.equals("Lib_history")) {//System.out.println(2);
                        HistoryDAO historyupload = new HistoryDAO();
                        historyupload.uploadHistory(object, con);
                        Thread.sleep(2000);
                        con.notifyAll();
                        return;
                    }
                    ; } catch (BatchUpdateException bu) {
                    System.out.println("ожидание..");
                    //bu.printStackTrace();
                    try {
                        con.rollback();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    con.wait();
            }
                catch (SQLException e) {
                e.printStackTrace();
                System.out.println("тут!");
            }
            } }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
