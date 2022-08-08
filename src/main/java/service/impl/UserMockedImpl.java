package service.impl;

import dto.CreateUserDto;
import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMockedImpl implements UserService {
    private final List<User> users;

    public UserMockedImpl() {
        users = new ArrayList<>();
        users.add(new User(1, "Ricardo", "ricardo@example.com","123"));
        users.add(new User(1, "Horacio","horacio@example.com","123"));
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        User user = new User(users.get(users.size()-1).id, createUserDto.name, createUserDto.email , createUserDto.password);
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        return users.stream().filter(user -> user.id == id).findFirst().orElse(null);
    }

    @Override
    public void removeUser(Integer id) {
        users.remove(users.stream().filter(user -> user.id == id).findFirst().orElse(null));
    }

    @Override
    public Optional<User> login(String email, String password) {
        return users.stream().filter(user -> user.email.equals(email) && user.password.equals(password)).findFirst();
    }
}
