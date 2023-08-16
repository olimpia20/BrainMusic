package music.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Identifiable<Integer>, Serializable {

    Integer id;
    String first_name;
    String last_name;
    Date birthday;
    String username;  //unique
    String email;     //unique
    String password;

    public User(){}

    public User(String first_name, String last_name, Date birthday, String username, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
