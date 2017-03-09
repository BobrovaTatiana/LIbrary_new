package ru.bobrova.models.dao;

import marshaling.Lib_book;
import ru.bobrova.models.connector.AcademConnector;
import ru.bobrova.models.pojo.Books;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tanusha on 27/02/2017.
 */
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

    public static Lib_book loadBooks (Connection con) throws SQLException, ClassNotFoundException {
        Lib_book books = new Lib_book();
        books.setBooks("List of books");
        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".\"Books\"");
                while (rs.next()) {
                    Integer id = Integer.parseInt(rs.getString("id_books"));
                    String author = rs.getString("author").trim();
                    Date date = rs.getDate("date");
                    String genre = rs.getString("genre").trim();
                    String name = rs.getString("name").trim();
                    Integer year = rs.getInt("year");
                    String type_publication = rs.getString("type_publication").trim();

                    books.getBook().add(createBook(id, author, date, genre,
                            name, year, type_publication));
                }
                rs.close();
                //stmt.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Books createBook(int id, String author, Date date, String genre,
                                   String name, int year, String type) throws ParseException {
        Books book = new Books();
        book.setId_book(id);
        book.setAuthor(author);
        book.setDate_pub(date);
        book.setGenre(genre);
        book.setName(name);
        book.setType_publication(type);
        book.setYear(year);
        return book;
    }

    public void uploadBooks (Object object, Connection dbConnection) throws SQLException, ClassNotFoundException, InterruptedException {
        Lib_book books = (Lib_book) object;
        String insertTableSQL = "INSERT INTO public.\"Books\"(\n" +
                "\t\"id_books\", \"type_publication\", \"name\", \"year\", \"date\", \"author\", \"genre\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepareStatement = dbConnection.prepareStatement(insertTableSQL);
        dbConnection.setAutoCommit(false);

        for (int i = 0; i < books.getBook().size(); i++) {
            prepareStatement.setInt(1, books.getBook().get(i).getId_book());
            prepareStatement.setString(2, books.getBook().get(i).getType_publication());
            prepareStatement.setString(3, books.getBook().get(i).getName());
            prepareStatement.setInt(4, books.getBook().get(i).getYear());
            if (books.getBook().get(i).getDate_pub() != null)
                prepareStatement.setDate(5, new java.sql.Date(books.getBook().get(i).getDate_pub().getTime()));
            else prepareStatement.setDate(5, null);
            prepareStatement.setString(6, books.getBook().get(i).getAuthor());
            prepareStatement.setString(7, books.getBook().get(i).getGenre());
            prepareStatement.addBatch();
        }
        prepareStatement.executeBatch();
        dbConnection.commit();

        System.out.println("Record is inserted into Books table!");
        prepareStatement.close();
    }

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
}