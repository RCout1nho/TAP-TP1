package dto;

import model.enumerators.UserTypeEnum;

public class CreateUserDto {
    private final String name;
    private final String email;
    private final UserTypeEnum type;
    private String password;

    public CreateUserDto(String name, String email, UserTypeEnum type, String password) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
