package ru.bobrova.models.pojo;

/**
 * Created by Tanusha on 20/02/2017.
 */
public class Text_book {
    private int id_book;
    private String text;

    public Text_book(int id_book, String text) {
        this.id_book = id_book;
        this.text = text;
    }

    public int getId_book() {
        return id_book;
    }

    public String getText() {
        return text;
    }
}
