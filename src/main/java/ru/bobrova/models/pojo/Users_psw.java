package ru.bobrova.models.pojo;

/**
 * Created by Tanusha on 20/02/2017.
 */
public class Users_psw {
    private int id_user;
    private String password;

    public Users_psw(int id_user, String password) {
        this.id_user = id_user;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public String getPassword() {
        return password;
    }
}
