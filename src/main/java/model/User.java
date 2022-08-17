package model;

import model.enumerators.UserTypeEnum;

public class User {
    public Integer id;
    public String name;
    public String email;
    public UserTypeEnum type;
    public String password;

    public User(int id, String name, String email, UserTypeEnum type, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, UserTypeEnum type) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
