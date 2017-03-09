package marshaling;

import ru.bobrova.models.pojo.Books;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanusha on 20/02/2017.
 */
@XmlType(propOrder = { "books", "book" }, name = "library")
@XmlRootElement
public class Lib_book {
    private List<Books> book = new ArrayList<Books>();
    private String books;

    public String getBooks() {
        return books;
    }

    public List<Books> getBook() {
        return book;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public void setBook(List<Books> book) {
        this.book = book;
    }
}
