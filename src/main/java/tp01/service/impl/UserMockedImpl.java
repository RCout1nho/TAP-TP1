package tp01.service.impl;

import tp01.dto.CreateUserDto;
import tp01.model.User;
import tp01.model.enumerators.UserTypeEnum;
import tp01.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserMockedImpl implements UserService {
    private final List<User> users;

    public UserMockedImpl() {
        users = new ArrayList<>();
        users.add(new User(1, "Ricardo", "ricardo@example.com", UserTypeEnum.ADMIN,"123"));
        users.add(new User(1, "Horacio","horacio@example.com", UserTypeEnum.ADMIN,"123"));
    }

    @Override
    public Boolean createUser(CreateUserDto createUserDto) {
        User user = new User(users.get(users.size()-1).getId(), createUserDto.getName(), createUserDto.getEmail() , createUserDto.getType(),  createUserDto.getPassword());
        users.add(user);
        return true;
    }

    @Override
    public List<User> getClientUsers() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void removeUser(Integer id) {
        users.remove(users.stream().filter(user -> user.getId() == id).findFirst().orElse(null));
    }

    @Override
    public User login(String email, String password) {
        return users.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().orElse(null);
    }
}
