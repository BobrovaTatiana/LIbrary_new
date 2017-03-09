package ru.bobrova.services;

import ru.bobrova.models.dao.BookDAO;
import ru.bobrova.models.pojo.Books;

import java.util.List;

/**
 * Created by Tanusha on 27/02/2017.
 */
public class BookService {
    public static List<Books> getAllBooks(){
        return BookDAO.getAllBooks();
    }

    public static boolean deleteBook(int id) {
        return BookDAO.deleteBook(id);
    }

    public static boolean updateBook(Books book) {
        return BookDAO.updateBook(book);
    }

    public static boolean insertBook(Books book) {
        return BookDAO.insertBook(book);
    }
}
