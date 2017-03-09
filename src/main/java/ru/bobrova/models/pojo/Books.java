package ru.bobrova.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by Tanusha on 20/02/2017.
 */

@XmlType(propOrder = { "id_book", "type_publication", "name","date_pub",
        "year", "author", "genre" }, name = "Books")
@XmlAccessorType(XmlAccessType.FIELD)
public class Books {
    @XmlElement(type=int.class)
    private int id_book;
    @XmlElement(type=String.class)
    private String type_publication;
    @XmlElement(type=String.class)
    private String name;
    @XmlElement(type=Date.class)
    private Date date_pub;
    @XmlElement(type=int.class)
    private int year;
    @XmlElement(type=String.class)
    private String author;
    @XmlElement(type=String.class)
    private String genre;

    public Books(int id_book, String type_publication, String name, Date date_pub, int year, String author, String genre) {
        this.id_book = id_book;
        this.type_publication = type_publication;
        this.name = name;
        this.date_pub = date_pub;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Books() {
    }

    public int getId_book() {
        return id_book;
    }

    public String getType_publication() {
        return type_publication;
    }

    public String getName() {
        return name;
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public void setType_publication(String type_publication) {
        this.type_publication = type_publication;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
