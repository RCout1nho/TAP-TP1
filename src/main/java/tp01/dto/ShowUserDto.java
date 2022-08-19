package tp01.dto;

import tp01.model.enumerators.UserTypeEnum;

public class ShowUserDto {
    private final Integer id;
    private final String name;
    private final String email;
    private final UserTypeEnum type;


    public ShowUserDto(Integer id, String name, String email, UserTypeEnum type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
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
}
