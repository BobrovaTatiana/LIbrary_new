package ru.bobrova.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bobrova.models.connector.AcademConnector;
import ru.bobrova.models.pojo.Books;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanusha on 27/02/2017.
 */
@Component
public class BookDAO {
    private static Logger logger = Logger.getLogger(BookDAO.class);

    private static String SQL_ALL_BOOK = "SELECT * FROM \"public\".\"Books\"";

    private static final String SQL_DELETE_BOOK =
            "DELETE FROM \"public\".\"Books\" WHERE\n" +
                    "\t\"id_books\" = ?;";
    private static String SQL_ID_BOOK = "SELECT * FROM \"public\".\"Books\" WHERE \"id_books\"=?;";
    private static String SQL_UPDATE_BOOK = "UPDATE \"public\".\"Books\"\n" +
            "\tSET \"type_publication\"=?, \"name\"=?, \"year\"=?, \"date\"=?, \"author\"=?, \"genre\" = ?\n" +
            "\tWHERE \"id_books\" = ?;";
    private static String SQL_CREATE_BOOK = "INSERT INTO \"public\".\"Books\"(\n" +
            "\t\"type_publication\", \"name\", \"year\", \"date\", \"author\", \"genre\")\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?);";
    private static String SQL_TEXT_BOOK = "SELECT * FROM \"public\".\"Texts\" WHERE \"id_book\"=?;";
    private static String SQL_CREATE_COMMENT = "INSERT INTO public.\"Comments\"(\n"+
            "\tid_book, id_user, date, comment, rating)\n"+
            "\tVALUES (?, ?, ?, ?, ?);";

    @Autowired
    public static List<Books> getAllBooks(){
        List<Books> bookList = new ArrayList<>();
        try(Connection connection = AcademConnector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_BOOK);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Books book = new Books(
                        resultSet.getInt("id_books"),
                        resultSet.getString("type_publication"),
                        resultSet.getString("name"),
                        resultSet.getDate("date"),
                        resultSet.getInt("year"),
                        resultSet.getString("author"),
                        resultSet.getString("genre")

                );
                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return bookList;
    }

    @Autowired
    public static boolean deleteBook(int id){

        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK)) {
            preparedStatement.setInt(1, id);

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("delete " + count);
                return true;
            }else{
                logger.debug("Book with id " + id + " not delete");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Autowired
    public static Books getBookByID(int id){
        Books book = new Books();
        try(Connection connection = AcademConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ID_BOOK); {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    book = new Books(
                            resultSet.getInt("id_books"),
                            resultSet.getString("type_publication"),
                            resultSet.getString("name"),
                            resultSet.getDate("date"),
                            resultSet.getInt("year"),
                            resultSet.getString("author"),
                            resultSet.getString("genre")
                    );} else {
                    logger.debug(id + " was not found");
                }
                logger.trace( "Book " + book.getName()); }
                connection.close();
        } catch (SQLException e1) {
            logger.error(e1);
        }
        return book;
    }

    @Autowired
    public static boolean updateBook(Books book){
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK)) {

            preparedStatement.setString(1, book.getType_publication());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setDate(4, new java.sql.Date(book.getDate_pub().getTime()));
            preparedStatement.setString(5, book.getAuthor());
            preparedStatement.setString(6, book.getGenre());
            preparedStatement.setInt(7, book.getId_book());

            preparedStatement.executeUpdate();
            logger.trace( "book " + book.getName() + " updated");
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Autowired
    public static boolean insertBook(Books book){
        try(Connection connection = AcademConnector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_BOOK)) {
            preparedStatement.setString(1, book.getType_publication());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setDate(4, new java.sql.Date(book.getDate_pub().getTime()));
            preparedStatement.setString(5, book.getAuthor());
            preparedStatement.setString(6, book.getGenre());

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                connection.close();
                return true;
            }else{
                logger.debug("Book with name " + book.getName() + " not insert");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Autowired
    public static String getTextByID(int id){
        String text = "";
        try(Connection connection = AcademConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_TEXT_BOOK); {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                            text = resultSet.getString("text")
                            ;} else {
                    logger.debug(id + " was not found");
                }
            }
            connection.close();
        } catch (SQLException e1) {
            logger.error(e1);
        }
        return text;
    }

    @Autowired
    public static boolean insertComment(int id_book, int id_user, String comment, int rating){
        java.util.Date date = new java.util.Date();
        try(Connection connection = AcademConnector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_COMMENT)) {
            preparedStatement.setInt(1, id_book);
            preparedStatement.setInt(2, id_user);
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            preparedStatement.setString(4, comment);
            preparedStatement.setInt(5, rating);

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted comments" + count);
                connection.close();
                return true;
            }else{
                logger.debug("Comments for book " + id_book + " not insert");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }
}