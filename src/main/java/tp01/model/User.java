package tp01.model;

import tp01.model.enumerators.UserTypeEnum;

public class User {
    private Integer id;
    private String name;
    private String email;
    private UserTypeEnum type;
    private String password;

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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }
}
