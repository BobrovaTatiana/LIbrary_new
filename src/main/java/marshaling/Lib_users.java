package marshaling;

import ru.bobrova.models.pojo.Users;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanusha on 21/02/2017.
 */
@XmlType(propOrder = { "users", "user" }, name = "library")
@XmlRootElement
public class Lib_users {
    private List<Users> user = new ArrayList<Users>();
    private String users;

    public List<Users> getUser() {
        return user;
    }

    public String getUsers() {
        return users;
    }

    public void setUser(List<Users> user) {
        this.user = user;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
