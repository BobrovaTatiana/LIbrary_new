package ru.bobrova.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by Tanusha on 20/02/2017.
 */
@XmlType(propOrder = { "id_user", "firstname", "lastname","date_reg",
        "email", "login" }, name = "Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement(type=Integer.class)
    private int id_user;
    @XmlElement(type=String.class)
    private String firstname;
    @XmlElement(type=String.class)
    private String lastname;
    @XmlElement(type=Date.class)
    private Date date_reg;
    @XmlElement(type=String.class)
    private String email;
    @XmlElement(type=String.class)
    private String login;
    private String role;
    private String password;

    public Users(int id_user, String firstname, String lastname, Date date_reg, String email,
                 String login, String role, String password) {
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.date_reg = date_reg;
        this.email = email;
        this.login = login;
        this.role = role;
        this.password = password;
    }

    public Users() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getDate_reg() {
        return date_reg;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDate_reg(Date date_reg) {
        this.date_reg = date_reg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
