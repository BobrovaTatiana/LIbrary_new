package ru.bobrova.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by Tanusha on 20/02/2017.
 */
@XmlType(propOrder = { "id", "id_user", "id_book","date_red" }, name = "Histories")
@XmlAccessorType(XmlAccessType.FIELD)
public class History {
    @XmlElement(type=int.class)
    private int id;
    @XmlElement(type=int.class)
    private int id_user;
    @XmlElement(type=int.class)
    private int id_book;
    @XmlElement(type=Date.class)
    private Date date_red;

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_book() {
        return id_book;
    }

    public Date getDate_red() {
        return date_red;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public void setDate_red(Date date_red) {
        this.date_red = date_red;
    }
}
