package ru.bobrova.services;

import org.springframework.stereotype.Service;
import ru.bobrova.models.dao.BookDAO;
import ru.bobrova.models.pojo.Books;

import java.util.List;

/**
 * Created by Tanusha on 27/02/2017.
 */
@Service
public class BookService implements IBookService{
    private BookDAO bookDAO;

    @Override
    public List<Books> getAllBooks(){
        return bookDAO.getAllBooks();
    }

    @Override
    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    @Override
    public boolean updateBook(Books book) {
        return bookDAO.updateBook(book);
    }

    @Override
    public boolean insertBook(Books book) {
        return bookDAO.insertBook(book);
    }

    @Override
    public Books getBookByID(int id){
        return bookDAO.getBookByID(id);
    }

    @Override
    public String getTextByID(int id){
        return bookDAO.getTextByID(id);
    };

    @Override
    public boolean insertComment(int id_book, int id_user, String comment, int rating){
        return bookDAO.insertComment(id_book, id_user, comment, rating);
    }
}
